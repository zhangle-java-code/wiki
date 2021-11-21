package com.zh.wiki.config;

import com.zh.wiki.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Resource
    LogInterceptor logInterceptor;

    public void  addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/login");
    }

    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    registry.addMapping("/**")
    //            .allowedOriginPatterns("*")
    //            .allowedHeaders(CorsConfiguration.ALL)
    //            .allowedMethods(CorsConfiguration.ALL)
    //            .allowCredentials(true)
    //            .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    //}

}
