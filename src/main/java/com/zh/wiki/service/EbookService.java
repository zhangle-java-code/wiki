package com.zh.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.wiki.domain.Ebook;
import com.zh.wiki.domain.EbookExample;
import com.zh.wiki.mapper.EbookMapper;
import com.zh.wiki.req.EbookQueryReq;
import com.zh.wiki.req.EbookSaveReq;
import com.zh.wiki.resp.EbookQueryResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.util.CopyUtil;
import com.zh.wiki.util.SnowFlake;
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

    @Resource
    private SnowFlake snowFlake;

    /**
     * 查询列表
     * @param req
     * @return
     */
    public PageResp<EbookQueryResp> list(EbookQueryReq req) {

        EbookExample ebookExample = new EbookExample();                 //! 1. 条件查询: Example //! 2. 创建查询实例
        EbookExample.Criteria criteria = ebookExample.createCriteria();   // 相当于where条件
        //! 3. 添加查询条件
        //! 4. 二级分类查询
        if (!ObjectUtils.isEmpty(req.getName())) {
            // 左匹配和有匹配
            criteria.andNameLike("%" + req.getName() + "%"); // 模糊查询
        }
        if (!ObjectUtils.isEmpty(req.getCategoryId2())) {
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        }
        //! 5. 通用查询条件
        // ? PageHelper.startPage 直接支持分页
        // !PageHelper 只对第一次查询有效, 后续查询不会自动分页
        // !根据条件查询
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        //! 6. 封装分页信息
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        //! {} 占位符
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        // ! 复制属性进行封装
        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebookList) {
        //     // EbookResp ebookResp = new EbookResp();
        //     // BeanUtils.copyProperties(ebook, ebookResp);
        //     // 对象复制
        //     EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
        //
        //     respList.add(ebookResp);
        // }

        //! 7. 封装返回结果，转移到查询响应中
        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        //! 8. 封装返回结果
        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 普通查询列表
     * @param req
     * @return
     */
    public List<EbookQueryResp> listSimple(EbookQueryReq req) {

        List<Ebook> ebookList = ebookMapper.selectByExample(null);
        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        return list;
    }

    public List<EbookQueryResp> listV1(EbookQueryReq req) {
        //! 1. 条件查询: Example
        //! 2. 创建查询实例
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        //! 3. 添加查询条件
        //! 4. 二级分类查询
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(req.getCategoryId2())) {
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        }
        //! 5. 通用查询条件
        PageHelper.startPage(req.getPage(), req.getSize());
        // !根据条件查询
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        //! 6. 封装分页信息
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        // ! 复制属性进行封装
         List<EbookQueryResp> respList = new ArrayList<>();
         for (Ebook ebook : ebookList) {
             EbookQueryResp ebookResp = new EbookQueryResp();
              BeanUtils.copyProperties(ebook, ebookResp);
             // 对象复制
             //EbookQueryResp ebookResp = CopyUtil.copy(ebook, EbookQueryResp.class);

             respList.add(ebookResp);
         }

        //! 7. 封装返回结果，转移到查询响应中
        //List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        return respList;
    }


    /**
     * 保存
     */
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        // ! 判断新增还是编辑
        if (ObjectUtils.isEmpty(req.getId())) {
            // ! 新增
            // TODO: 2020/3/24 新增时，需要设置主键
            // !雪花法算法
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        } else {
            // ! 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}
