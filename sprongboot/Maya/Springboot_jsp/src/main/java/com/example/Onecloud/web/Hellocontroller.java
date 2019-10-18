package com.example.Onecloud.web;

import com.example.Onecloud.mapper.helloMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @description:测试1
 * @version:1
 * @author:XZX
 * @date:2019/3/21
 */
@Controller
@RequestMapping
public class Hellocontroller {
   @Autowired
   private helloMapper hd;
    @RequestMapping("/getList")
    public ModelAndView getList(ModelAndView m) throws Exception {
        /*if(true){
            throw new  Exception("sdas");
        }*/
        PageHelper.startPage(1,10);
        PageHelper page=new PageHelper();
        Properties p=new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        page.setProperties(p);
        List<Map> map=new ArrayList<>();
        map=hd.getlist();
        PageInfo pg=new PageInfo(map);
        ModelAndView mv=new ModelAndView();
        mv.addObject("exception", pg.getSize());
        mv.addObject("url", pg.getList());
        mv.setViewName("hello");
        //m.addAttribute("info",pg);
        return mv;
    }

    @PutMapping("/upload")
    public java.lang.String putupload(HttpServletRequest re, @RequestParam("file") MultipartFile mf, Model m) throws  Exception{
        if (mf.isEmpty()) {
            m.addAttribute("info","文件为空");
        }
        //创建文件名
        try {
            java.lang.String filename=System.currentTimeMillis()+mf.getOriginalFilename();
            //创建地址
            java.lang.String destPath=re.getServletContext().getRealPath("")+"upload"+ File.separator+filename;
            //创建路径
            File destFile = new File(destPath);
            destFile.getParentFile().mkdir();
            //复制文件到指定目录
            mf.transferTo(destFile);

            m.addAttribute("fileName",filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "hello";
    }

    @GetMapping("/upload")

    public java.lang.String getupload(HttpServletRequest re, @RequestParam("file") MultipartFile mf, Model m) throws  Exception{
        if (mf.isEmpty()) {
            m.addAttribute("info","文件为空");
        }
        //创建文件名
        try {
            java.lang.String filename=System.currentTimeMillis()+mf.getOriginalFilename();
            //创建地址
            java.lang.String destPath=re.getServletContext().getRealPath("")+"upload"+ File.separator+filename;
            //创建路径
            File destFile = new File(destPath);
            destFile.getParentFile().mkdir();
            //复制文件到指定目录
            mf.transferTo(destFile);

            m.addAttribute("fileName",filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "showImg";
    }

    @DeleteMapping ("/upload")
    public java.lang.String deleteupload(HttpServletRequest re, @RequestParam("file") MultipartFile mf, Model m) throws  Exception{
        if (mf.isEmpty()) {
            m.addAttribute("info","文件为空");
        }
        //创建文件名
        try {
            java.lang.String filename=System.currentTimeMillis()+mf.getOriginalFilename();
            //创建地址
            java.lang.String destPath=re.getServletContext().getRealPath("")+"upload"+ File.separator+filename;
            //创建路径
            File destFile = new File(destPath);
            destFile.getParentFile().mkdir();
            //复制文件到指定目录
            mf.transferTo(destFile);

            m.addAttribute("fileName",filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "showImg";
    }

    @PostMapping("/upload")
    public java.lang.String postupload(HttpServletRequest re, @RequestParam("file") MultipartFile mf, Model m) throws  Exception{
        if (mf.isEmpty()) {
            m.addAttribute("info","文件为空");
        }
        //创建文件名
        try {
            java.lang.String filename=System.currentTimeMillis()+mf.getOriginalFilename();
            //创建地址
            java.lang.String destPath=re.getServletContext().getRealPath("")+"upload"+ File.separator+filename;
            //创建路径
            File destFile = new File(destPath);
            destFile.getParentFile().mkdir();
            //复制文件到指定目录
            mf.transferTo(destFile);

            m.addAttribute("fileName",filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "showImg";
    }

}