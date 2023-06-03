package com.zh.wiki.controller;


import com.zh.wiki.req.CategoryQueryReq;
import com.zh.wiki.req.CategorySaveReq;
import com.zh.wiki.resp.CategoryQueryResp;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    // ? Resource装饰器作用
    @Resource
    private CategoryService categoryService;

    // ?  分类没有做层级嵌套，只是简单的返回了一个列表
    // ebook也是只是有两个层级

    // ?  分类没有做层级嵌套，只是简单的返回了一个列表
    // ebook也是只是有两个层级



    @GetMapping("/all")
    public CommonResp all(){
        // TODO 接口未将数据未做层级嵌套
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        return resp;
    }

    // 分类列表实现
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