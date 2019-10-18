package com.example.consumer.feignHystrix;

import com.example.consumer.feignInterface.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xzx
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String getList(@RequestParam(value = "name") String name) {
        return "Hello i'm hystrix";
    }
}
