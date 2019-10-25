package com.example.demo.controller;

import com.example.demo.pojo.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo.pojo.JsonResult.*;

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
           return result.failure(Meta.Err2);

       }else if(e.getLocalizedMessage()=="密码不正确"){
           return result.failure(Meta.Err4);
       }else if(e.getLocalizedMessage()=="重复登陆"){
           return result.failure(Meta.Err5);
       }
       else
           return result.failure(Meta.Err500,"{\"Messge\":\""+e.getMessage()+"\"}");
    }


}
