package com.example.demo.controller;

import com.example.demo.comm.Response;
import com.example.demo.pojo.BizExceptionType;
import com.example.demo.service.IBizExceptionTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 异常类别Controller
 * lxw
 * 2019-07-25
 */
 @RestController
 @RequestMapping("/bizExceptionType")
public  class BizExceptionTypeController {

@Autowired
private IBizExceptionTypeService bizExceptionTypeService;

/**
 *获取分页数据
 */
@RequestMapping("/getData")
public Response list(@RequestParam Map map) throws Exception {
    PageInfo pageInfo = bizExceptionTypeService.getList(map);
    return new Response().success(pageInfo.getList(), pageInfo.getTotal());
}

/**
 *获取实体
 */
@RequestMapping("/getOne")
public Response list(String etype) throws Exception {
    return new Response().success(bizExceptionTypeService.getModel(etype));
}

/**
 *保存
 */
@RequestMapping("/add")
public Response Save(BizExceptionType record) throws Exception {
    bizExceptionTypeService.add(record);
    return new Response().success();
}

/**
 *修改
 */
@RequestMapping("/edit")
public Response Edit(BizExceptionType record) throws Exception {
    bizExceptionTypeService.update(record);
    return new Response().success();
}

/**
 *删除
 */
@RequestMapping("/delete")
public Response remove(String etype) throws Exception {
    bizExceptionTypeService.delete(etype);
    return new Response().success();
}
}
