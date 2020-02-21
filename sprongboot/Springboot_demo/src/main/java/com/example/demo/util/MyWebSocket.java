package com.example.demo.util;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;

import java.io.IOException;

/**
 * socket测试类
 */
@ServerEndpoint(prefix = "netty-websocket")
@Component
public class MyWebSocket {

    /**
     * 当有新的WebSocket连接进入时，对该方法进行回调
     * 注入参数的类型:Session、HttpHeaders、ParameterMap
     * @param session
     * @param headers
     * @param parameterMap
     * @throws IOException
     */
    @OnOpen
    public void Onopen(Session session, HttpHeaders headers, ParameterMap parameterMap)throws IOException {
        System.out.println("-------新的连接-----------");
        String paramValue = parameterMap.getParameter("paramKey");
        System.out.println(paramValue);
    }

    /**当有WebSocket连接关闭时，对该方法进行回调
     注入参数的类型:Session
     * @param session
     * @throws IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
    }

    /**
     * 当有WebSocket抛出异常时，对该方法进行回调
     * 注入参数的类型:Session、Throwable
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 当接收到字符串消息时，对该方法进行回调
     * 注入参数的类型:Session、String
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        session.sendText("Hello Netty!");
    }

    /**
     * 当接收到二进制消息时，对该方法进行回调
     * 注入参数的类型:Session、byte[]
     * @param session
     * @param bytes
     */
    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    /**
     * 当接收到Netty的事件时，对该方法进行回调
     * 注入参数的类型:Session、Object
     * @param session
     * @param evt
     */
    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}
