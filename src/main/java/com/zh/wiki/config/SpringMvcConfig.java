package com.zh.wiki.config;

import com.zh.wiki.interceptor.ActionInterceptor;
import com.zh.wiki.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    @Resource
    ActionInterceptor actionInterceptor;

    //? 拦截器执行顺序，从上到下，从左到右
    public void addInterceptors(InterceptorRegistry registry) {
        //!拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/mom/**",
                        "/test/**",
                        "/hello/**",
                        "/redis/**",
                        "/user/login",
                        "/category/all",
                        "/ebook/list/**",
                        "/doc/all/**",
                        "/doc/vote/**",
                        "/doc/find-content/**",
                        "/ebook-snapshot/**"
                );


        //! 拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印
        // !权限拦截
        registry.addInterceptor(actionInterceptor)
                .addPathPatterns(
                        "/*/save",
                        "/*/delete/**",
                        "/*/reset-password");
    }
}
