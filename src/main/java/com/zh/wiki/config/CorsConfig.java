package com.zh.wiki.config;

import com.zh.wiki.aspect.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// !跨域配置
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        LOG.warn("跨域配置----------------》》》》》");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

}
