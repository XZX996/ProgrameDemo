package com.example.demo.web;

import com.example.demo.Dao.helloMapper;
import com.example.demo.pojo.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RestController
@RequestMapping("/hello")
public class Hellocontroller {
   @Autowired
   helloMapper hd;
    @RequestMapping(value = "/getList")
    public JsonResult getList() throws Exception {

        PageHelper.startPage(1,10);
        PageHelper page=new PageHelper();
        Properties p=new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        page.setProperties(p);
        List<Map> map1=new ArrayList<>();
        map1=hd.getlist();
        PageInfo pg=new PageInfo(map1);
        return new JsonResult().success(pg);
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