package com.example.servercloud.common;

import com.example.servercloud.pojo.JsonResult;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandel {
    @ExceptionHandler(value = Exception.class)
    public JsonResult defaultErrorHandel(HttpServletRequest re, Exception e)throws Exception{
       /* ModelAndView mv=new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", re.getRequestURL());
        mv.setViewName("errorPage");
        return mv;*/
        JsonResult result=new JsonResult();
        if(e.getLocalizedMessage()=="用户名不正确"){
            return result.failure(JsonResult.Meta.Err2);

        } else if(e instanceof ZuulRuntimeException){
            return result.failure(JsonResult.Meta.Err404);
        }
        else
            return result.failure(JsonResult.Meta.Err500,"{\"Messge\":\""+e.getMessage()+"\"}");
    }
}
