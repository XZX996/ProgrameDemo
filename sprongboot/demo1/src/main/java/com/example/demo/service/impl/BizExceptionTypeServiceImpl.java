package com.example.demo.service.impl;

import com.example.demo.mapper.BizExceptionTypeMapper;
import com.example.demo.pojo.BizExceptionType;
import com.example.demo.service.IBizExceptionTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 异常类别ServiceImpl
 * lxw
 * 2019-07-25
 */
 @Service
public  class BizExceptionTypeServiceImpl implements IBizExceptionTypeService {

    @Autowired
    private BizExceptionTypeMapper bizExceptionTypeMapper;

    /**
     *新增
     */
    @Override
    public void add(BizExceptionType record) throws Exception {
        bizExceptionTypeMapper.insertSelective(record);
    }
    
    /**
     *修改
     */
    @Override
    public void update(BizExceptionType record) throws Exception {
        bizExceptionTypeMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     *删除
     */
    @Override
    public void delete(String etype) throws Exception {
        bizExceptionTypeMapper.deleteByPrimaryKey(etype);
    }
    
    /**
     *获取实体
     */
    @Override
    public BizExceptionType getModel(String etype) throws Exception {
        return bizExceptionTypeMapper.selectByPrimaryKey(etype);
    }
    
    /**
     *获取分页数据
     */
    @Override
    public PageInfo getList(Map map) throws Exception {
        int pageNum = 1;
        int pageSize = 10;
        if (!StringUtils.isEmpty(map.get("page"))) {
            pageNum = Integer.parseInt((String) map.get("page"));
        }
        if (!StringUtils.isEmpty(map.get("limit"))) {
            pageSize = Integer.parseInt((String) map.get("limit"));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<BizExceptionType> list = bizExceptionTypeMapper.selectAllByMap(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
