package com.zh.wiki.controller;


import com.zh.wiki.domain.Test;
import com.zh.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//! 代码返回页面
//@Controller
//! 返回文本
@RestController
public class TestController {

    // @Resource和Overrides区别
    @Resource
    private TestService testService;

    //! 获取环境变量值, 获取配置文件
    @Value("${test.hello:Test}")
    private String testHello;

    //! 支持所有请求方式: RequestMapping 形式
    //  编写方式不一致
    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    // @RequestMapping(value = "/hello/1", method = RequestMethod.GET)
    // @RequestMapping("/hello")
    // 简写
    @GetMapping("/hello")
    //@PostMapping("/hello")
    //@PutMapping("/hello")
    //@DeleteMapping("/hello")
    public String hello(){
        return "hello world2," + testHello;
    }


    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "hello world! post, " + name;
    }


    @PostMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }

}
