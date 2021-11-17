package com.zh.wiki.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//! 代码返回页面
//@Controller
//! 返回文本
@RestController
public class TestController {

    //! 支持所有请求方式
    //@RequestMapping("/hello")
    @GetMapping("/hello")
    //@PostMapping("/hello")
    //@PutMapping("/hello")
    //@DeleteMapping("/hello")
    public String hello(){
        return "hello world1";
    }
}
