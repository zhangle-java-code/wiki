package com.zh.wiki.controller;


import com.zh.wiki.req.CategoryQueryReq;
import com.zh.wiki.req.CategorySaveReq;
import com.zh.wiki.resp.CategoryQueryResp;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public CommonResp list(CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> pageResp = categoryService.list(req);
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody CategorySaveReq req){
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}