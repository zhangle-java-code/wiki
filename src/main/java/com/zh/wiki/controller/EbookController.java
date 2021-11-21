package com.zh.wiki.controller;

import com.zh.wiki.domain.Ebook;
import com.zh.wiki.service.EbookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;


    @PostMapping("/list")
    public List<Ebook> list() {
        return ebookService.list();
    }
}
