package com.zh.wiki.service;

import com.zh.wiki.domain.Ebook;
import com.zh.wiki.domain.EbookExample;
import com.zh.wiki.mapper.EbookMapper;
import com.zh.wiki.req.EbookReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;


    public List<Ebook> list(EbookReq req){
        // ! 查询条件固定写法
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        return  ebookMapper.selectByExample(ebookExample);
    }

}
