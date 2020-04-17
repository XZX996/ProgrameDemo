package com.example.servercloud.fiter;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
public class ZuulfailCallback implements FallbackProvider {
    @Override
    public String getRoute() {
        //*  为所有回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if(cause instanceof HystrixTimeoutException){
            return null;
        }
        return null;
    }

}
