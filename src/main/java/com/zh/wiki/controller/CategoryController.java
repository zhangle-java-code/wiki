package com.zh.wiki.controller;


import com.zh.wiki.req.CategoryQueryReq;
import com.zh.wiki.resp.CategoryQueryResp;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Resource
    private CategoryService categoryService;

    @GetMapping("list")
    public CommonResp list(CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> pageResp = categoryService.list(req);
        resp.setContent(pageResp);
        return resp;
    }
}