package com.xiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * @description:test
 * @version:1
 * @author:xzx
 * @date:2019/10/17
 */
@Controller
@RequestMapping("test")
public class testcontroller{

   @RequestMapping("/home")
   public ModelAndView test(){
       ModelAndView mav = new ModelAndView("home");
       mav.addObject("message", "Hello Spring MVC");
       return mav;
   }
    @RequestMapping("/home1")
    public String test2(){
        return "home1";
    }

   @RequestMapping("/json")
   @ResponseBody
   public String test1(){
       return "ssd";

   }
}