package com.zh.wiki.controller;

import com.zh.wiki.domin.Demo;
import com.zh.wiki.service.DemoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;


    @PostMapping("/list")
    public List<Demo> list(){
        return demoService.list();
    }
}
