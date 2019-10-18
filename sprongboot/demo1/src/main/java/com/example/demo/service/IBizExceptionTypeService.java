package com.example.demo.service;

import com.example.demo.pojo.BizExceptionType;
import com.github.pagehelper.PageInfo;
import java.util.Map;

/**
 * 异常类别Service
 * lxw
 * 2019-07-25
 */
public interface IBizExceptionTypeService {

    /**
     * 新增
     */
    void add(BizExceptionType record) throws Exception;

    /**
     * 修改
     */
    void update(BizExceptionType record) throws Exception;

    /**
     * 删除
     */
    void delete(String etype) throws Exception;

    /**
     * 获取分页数据
     */
    PageInfo getList(Map map) throws Exception;

    /**
     * 获取实体
     */
    BizExceptionType getModel(String etype) throws Exception;
    
}
