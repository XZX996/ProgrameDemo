package com.example.nacos_client.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:测试
 * @version:1
 * @author:xzx
 * @date:2019/12/26
 */
@RestController
@RequestMapping("Value")
public class Valuecontroller {
    @Autowired
    private RestTemplate restTemplate;
    @NacosInjected
    private NamingService namingService;

    @NacosValue(value = "${MyName:null}", autoRefreshed = true)
    private String username;
    @RequestMapping(value = "/getval")
    public String getUser() {
        return username;
    }

    @RequestMapping(value = "/getSerVal")
    public String getSerVal() {
        String serverName="zuul_service";
        String groupNmae="DEFAULT_GROUP";
        String api="/val/Value/getval";
        try {
            String msg= "hello";
            List<Instance> sers=namingService.getAllInstances(serverName,groupNmae);
            Instance instance=namingService.selectOneHealthyInstance(serverName,groupNmae);
            String url="http://"+instance.getIp()+":"+instance.getPort()+api+"?msg="+msg;
            String r=restTemplate.getForObject(url,String.class);
            return r;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * post方式传参调用nacos服务
     */
    @RequestMapping("/getClientValueByPost")
    public String getClientValueByPost(HttpServletRequest request) {
        String serverName = "user_service";
        String groupNmae = "DEFAULT_GROUP";
        String api = "/val/Value/getval";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            String message = "hello";//request.getParameter("message");
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("message", message);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

            Instance instance = namingService.selectOneHealthyInstance(serverName, groupNmae);
            String url = "http://" + instance.getIp() + ":" + instance.getPort() + api;
            String r = restTemplate.postForObject(url, httpEntity, String.class);
            return r;
        } catch (Exception e) {
            return "";
        }
    }
}
