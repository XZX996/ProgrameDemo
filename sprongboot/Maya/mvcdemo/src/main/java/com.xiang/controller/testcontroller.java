package com.xiang.controller;

import com.xiang.dto.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.Map;

/**
 * @description:test
 * @version:1
 * @author:xzx
 * @date:2019/10/17
 */
@RestController
@RequestMapping("/test")
public class testcontroller{

    @RequestMapping("/getList")
    public JsonResult getList(@RequestParam Map map){
        return new JsonResult().success("sussd");
    }
   @RequestMapping("/home")
   public ModelAndView test(){
       ModelAndView mav = new ModelAndView("/home/home");
       mav.addObject("message", "Hello Spring MVC");
       return mav;
   }
    @RequestMapping("/home1")
    public String test2(){
        return "Main";
    }

   @RequestMapping("/json")
   @ResponseBody
   public String test1(){
       return "ssd";

   }
}
