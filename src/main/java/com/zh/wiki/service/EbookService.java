package com.zh.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.wiki.domain.Ebook;
import com.zh.wiki.domain.EbookExample;
import com.zh.wiki.mapper.EbookMapper;
import com.zh.wiki.req.EbookReq;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.EbookResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;


    public PageResp<EbookResp> list(EbookReq req){
        // ! 查询条件固定写法
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();


        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // ! 查询条件固定写法
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        //! 查询总页数
        PageInfo<Ebook> pageInfo = new PageInfo<>();
        LOG.warn("总行数: {}", pageInfo.getTotal());
        LOG.info("总行数: {}", pageInfo.getPages());

        //List<EbookResp> respList = new ArrayList<>();
 //       for (Ebook ebook : ebooksList) {
 ///*           EbookResp ebookResp = new EbookResp();
 //           //ebookResp.setId(ebook.getId());
 //           //! 拷贝属性,讲一个对象属性copy另一个属性
 //           BeanUtils.copyProperties(ebook,ebookResp);*/
 //           // 功能函数复制
 //           //EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
 //           respList.add(ebookResp);
 //       }
 //       return respList;

        // ! 循环，进行属性赋值
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        PageResp<EbookResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

}
