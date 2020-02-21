package com.example.demo.server;

import com.example.demo.pojo.MsgLog;

import java.util.Date;
import java.util.List;

public interface msgLogServer {

    void updateStatus(String msgId, Integer status);

    MsgLog selectByMsgId(String msgId);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(String msgId, Date tryTime);
}
