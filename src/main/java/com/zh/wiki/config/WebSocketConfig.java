package com.zh.wiki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//! 声明配置类 SpringBoot会自动扫描该类，并将其中的Bean注入到Spring容器中
//! 将Bean注入Spring容器的五种方式
//https://blog.csdn.net/uuuyy_/article/details/122171250
//@Configuration + @Bean
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
