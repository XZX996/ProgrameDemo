package com.example.consumer.feignInterface;

import com.example.consumer.feignHystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hero
 */
@FeignClient(name = "cloud-producer",fallback = HelloRemoteHystrix.class)
public interface HelloRemote  {

    @RequestMapping("/hello/")
    String getList(@RequestParam(value = "name") String name);
}
