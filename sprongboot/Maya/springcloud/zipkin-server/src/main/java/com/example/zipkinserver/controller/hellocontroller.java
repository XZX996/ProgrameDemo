package com.example.zipkinserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @description:xzx
 * @version:1
 * @author:z
 * @date:2020/4/16
 */
@RestController
@RequestMapping("/hello")
public class hellocontroller {

    @RequestMapping("/hi")
    public String getList() throws Exception {
        return "hello";
    }


}
