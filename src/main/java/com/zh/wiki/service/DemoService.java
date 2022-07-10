package com.zh.wiki.service;

import com.zh.wiki.domain.Demo;
import com.zh.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        // ! 普通查询
        return demoMapper.selectByExample(null);
    }
}
