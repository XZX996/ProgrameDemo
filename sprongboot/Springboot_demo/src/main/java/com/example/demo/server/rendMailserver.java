
package com.example.demo.server;

import com.example.demo.pojo.Mail;
import com.example.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutionException;

@Service
public class rendMailserver {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailUtil mail1;

/**
     * 发送简单文本文件
     */


    public void sendSimpleEmail(String sendto){
        try {
            Mail mail=new Mail();
            mail.setTo(sendto);
            mail.setTitle("测试邮件");
            mail.setContent("sdsdadadadsd");
            mail.setMsgId("ss");
            mail1.send(mail);
        }catch (Exception e){
            System.out.println("发送简单文本文件-发生异常");
        }
    }


/**
     * 发送html文本
     * @param
     */

    @Async
    public void sendHTMLMail(){
        try {
            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"utf-8");
            messageHelper.setFrom("15902048215@163.com");
            messageHelper.setTo("XZZZ66D@163.com");
            messageHelper.setSubject("欢迎访问");
            messageHelper.setText("<a href='www.baidu.com'>百度</a>",true);

            mailSender.send(message);
        }catch (Exception e){
        }
    }
}

