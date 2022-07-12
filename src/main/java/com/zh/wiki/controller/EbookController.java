package com.zh.wiki.controller;

import com.zh.wiki.req.EbookQueryReq;
import com.zh.wiki.req.EbookSaveReq;
import com.zh.wiki.resp.CommonResp;
import com.zh.wiki.resp.EbookQueryResp;
import com.zh.wiki.resp.PageResp;
import com.zh.wiki.service.EbookService;
//? annotation
import org.springframework.web.bind.annotation.*;
//? Resource
import javax.annotation.Resource;
//? 校验参数
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list/simple")
    public CommonResp listSimple(@Valid EbookQueryReq req) {
        CommonResp<List<EbookQueryResp>> resp = new CommonResp<>();
        List<EbookQueryResp> list = ebookService.listSimple(null);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list/v1")
    public CommonResp listV1(@Valid EbookQueryReq req) {
        CommonResp<List<EbookQueryResp>> resp = new CommonResp<>();
        List<EbookQueryResp> list = ebookService.listV1(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp delete (@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
