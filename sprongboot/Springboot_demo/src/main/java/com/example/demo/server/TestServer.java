package com.example.demo.server;

import com.example.demo.common.ServerResponse;
import com.example.demo.pojo.Mail;

public interface TestServer {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
