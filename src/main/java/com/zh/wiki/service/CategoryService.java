package com.zh.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.wiki.domain.Category;
import com.zh.wiki.domain.CategoryExample;
import com.zh.wiki.mapper.CategoryMapper;
import com.zh.wiki.req.CategoryQueryReq;
import com.zh.wiki.req.CategorySaveReq;
import com.zh.wiki.req.EbookSaveReq;
import com.zh.wiki.resp.CategoryQueryResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.util.CopyUtil;
import com.zh.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    SnowFlake snowFlake;

    @Resource
    private CategoryMapper  categoryMapper;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {

        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>();

        // categoryList 查询结果是Category类型，需要转换成CategoryQueryResp类型
        List<CategoryQueryResp> list =  CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        PageResp<CategoryQueryResp> pageResp = new PageResp();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else{
            categoryMapper.updateByPrimaryKey(category);
        }
    }

}
