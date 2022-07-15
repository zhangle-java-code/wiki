package com.zh.wiki.service;

import com.zh.wiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//! 为了让异步化生效，需要在类上加上@Async注解
// !开启很多线程，添加一个线程池， 一但超过线程池最大值还是会变成同步的, 影响性能
// MQ分发和接受，两台服务器
@Service
public class WsService {

    @Resource
    public WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);
    }
}
