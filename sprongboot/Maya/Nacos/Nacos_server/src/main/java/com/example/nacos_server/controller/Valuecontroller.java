package com.example.nacos_server.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:测试
 * @version:1
 * @author:xzx
 * @date:2019/12/26
 */
@RestController
@RequestMapping("Value")
public class Valuecontroller {
    @NacosValue(value = "${username:null}", autoRefreshed = true)
    private String username;
    @RequestMapping(value = "/getval")
    public String getUser() {
        return username;
    }
}
