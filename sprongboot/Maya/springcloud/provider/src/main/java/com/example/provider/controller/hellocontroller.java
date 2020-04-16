package com.example.provider.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:测试
 * @version:1.0
 * @author:xzx
 * @date:2019/10/9
 */
@RestController
@RequestMapping("/hello")
public class hellocontroller {

    @RequestMapping("/hi")
    public String getList() throws Exception {
       return "hello";
    }

}
