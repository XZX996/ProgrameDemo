package com.example.consumer.controller;

import com.example.consumer.feignInterface.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:接受提供者信息
 * @version:1.0
 * @author:xzx
 * @date:2019/10/9
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    //@Autowired
    //private LoadBalancerClient client;
    //@Autowired
    //private RestTemplate restTemplate;
    @Autowired
    private HelloRemote hl;
   /* @RequestMapping("/we")
    public String getList(@RequestParam String name) throws Exception {
        name += "!";
        //使用LoadBalancerClient
        //ServiceInstance instance = client.choose("cloud-producer");
        // String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello/?name=" + name;
        //使用Ribbon
        String url = "http://cloud-producer/hello/?name=" + name;

        return restTemplate.getForObject(url, String.class);


    }*/

    @RequestMapping("/{name}")
    public String index(@PathVariable("name") String name){
        return hl.getList(name + "!");
    }

}