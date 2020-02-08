package com.example.demo.server.serimp;

import com.example.demo.Dao.MsgLogMapper;
import com.example.demo.common.ServerResponse;
import com.example.demo.config.RabbitMqConfig;
import com.example.demo.pojo.JsonResult;
import com.example.demo.pojo.Mail;
import com.example.demo.pojo.MsgLog;
import com.example.demo.server.TestServer;
import com.example.demo.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServerImpl implements TestServer {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitMqConfig.MAIL_EXCHANGE_NAME, RabbitMqConfig.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);// 消息入库

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitMqConfig.MAIL_EXCHANGE_NAME, RabbitMqConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);// 发送消息

        return ServerResponse.success(JsonResult.Meta.SUCCESS.getMessage());
    }

}
