package com.zh.wiki.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// ! 扫描包 @ComponentScan({"com.zh", "com.test"})
@ComponentScan("com.zh")
@SpringBootApplication
@MapperScan("com.zh.wiki.mapper")
// !启动定时任务
//@EnableScheduling
// !启动异步任务的 synchronized 异步
@EnableAsync
public class WikiApplication {

        private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);

        public static void main(String[] args) {
            SpringApplication app = new SpringApplication(WikiApplication.class);
            //! 环境变量
            Environment env = app.run(args).getEnvironment();
            LOG.info("启动成功！！");
            LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
        }

}
