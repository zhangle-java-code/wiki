package com.zh.wiki.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//! 代码返回页面
//@Controller
//! 返回文本
@RestController
public class TestController {

    //! 获取默认值
    @Value("${test.hello:Test}")
    private String testHello;

    //! 支持所有请求方式`
    //@RequestMapping("/hello")
    @GetMapping("/hello")
    //@PostMapping("/hello")
    //@PutMapping("/hello")
    //@DeleteMapping("/hello")
    public String hello(){
        return "hello world1," + testHello;
    }


    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "hello world! post, " + name;
    }
}
