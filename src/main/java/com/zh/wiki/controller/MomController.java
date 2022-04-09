package com.zh.wiki.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mom")
public class MomController {

    @GetMapping("/list")
    public String list() {
        return "zhangle";
    }
    @PostMapping("/update")
    public String update() {
        return "zhangle";
    }
}
