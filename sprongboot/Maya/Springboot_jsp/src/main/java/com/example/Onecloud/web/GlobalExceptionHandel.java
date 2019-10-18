package com.example.Onecloud.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandel {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandel(HttpServletRequest re,Exception e)throws Exception{
        ModelAndView mv=new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", re.getRequestURL());
        mv.setViewName("errorPage");
        return mv;
    }


}
