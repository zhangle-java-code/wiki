package com.zh.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.wiki.domain.Content;
import com.zh.wiki.domain.Doc;
import com.zh.wiki.domain.DocExample;
import com.zh.wiki.mapper.CategoryMapper;
import com.zh.wiki.mapper.ContentMapper;
import com.zh.wiki.mapper.DocMapper;
import com.zh.wiki.req.DocQueryReq;
import com.zh.wiki.req.DocSaveReq;
import com.zh.wiki.resp.DocQueryResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.util.CopyUtil;
import com.zh.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private ContentMapper contentMapper;

    public List<DocQueryResp> all(){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList  =  docMapper.selectByExample(docExample);
        List<DocQueryResp> list  = CopyUtil.copyList(docList,DocQueryResp.class);
        return list;
    }

    public PageResp<DocQueryResp> list(DocQueryReq req){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();

        PageHelper.startPage(req.getPage(),req.getSize());

        List<Doc> docList = docMapper.selectByExample(docExample);

        List<DocQueryResp> list =  CopyUtil.copyList(docList,DocQueryResp.class);

        PageInfo<Doc> pageInfo = new PageInfo<>();
        PageResp<DocQueryResp> pageResp = new PageResp();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }


    public void save(DocSaveReq req){
        Doc doc =  CopyUtil.copy(req,Doc.class);
        Content content = CopyUtil.copy(req,Content.class);
        if(ObjectUtils.isEmpty(req.getId())){

            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);

            content.setId(doc.getId());
            contentMapper.insert(content);
        }else{
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (count ==0 ){
                contentMapper.insert(content);
            }
        }
    }

    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }
}
