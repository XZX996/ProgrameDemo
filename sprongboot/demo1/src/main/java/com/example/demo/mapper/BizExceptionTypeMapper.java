package com.example.demo.mapper;

import com.example.demo.pojo.BizExceptionType;
import java.util.List;
import java.util.Map;

/**
 * 异常类别Mapper
 * lxw
 * 2019-07-25
 */
public interface BizExceptionTypeMapper {
    /**
     *全字段插入
     */
    int insert(BizExceptionType record);

    /**
     *对非空字段插入
     */
    int insertSelective(BizExceptionType record);

    /**
     *全字段更新
     */
    int updateByPrimaryKeySelective(BizExceptionType record);

    /**
     *对非空字段更新
     */
    int updateByPrimaryKey(BizExceptionType record);

    /**
     *通过主键字段删除
     */
    int deleteByPrimaryKey(String etype);

    /**
     *通过主键字段查询
     */
    BizExceptionType selectByPrimaryKey(String etype);

    /**
     *获取数据
     */
    List<BizExceptionType> selectAllByMap(Map map);}
