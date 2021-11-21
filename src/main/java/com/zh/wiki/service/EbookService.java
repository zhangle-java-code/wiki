package com.zh.wiki.service;

import com.zh.wiki.domain.Ebook;
import com.zh.wiki.domain.EbookExample;
import com.zh.wiki.mapper.EbookMapper;
import com.zh.wiki.req.EbookReq;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;


    public List<EbookResp> list(EbookReq req){
        // ! 查询条件固定写法
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebooksList) {
            EbookResp ebookResp = new EbookResp();
            //ebookResp.setId(ebook.getId());
            //! 拷贝属性,讲一个对象属性copy另一个属性
            BeanUtils.copyProperties(ebook,ebookResp);
            respList.add(ebookResp);
        }
        return respList;
    }

}
