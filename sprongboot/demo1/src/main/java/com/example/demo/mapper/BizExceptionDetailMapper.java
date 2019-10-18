package com.example.demo.mapper;

import com.example.demo.pojo.BizExceptionDetail;
import java.util.List;
import java.util.Map;

/**
 * 异常详情Mapper
 * lxw
 * 2019-07-25
 */
public interface BizExceptionDetailMapper {
    /**
     *全字段插入
     */
    int insert(BizExceptionDetail record);

    /**
     *对非空字段插入
     */
    int insertSelective(BizExceptionDetail record);

    /**
     *全字段更新
     */
    int updateByPrimaryKeySelective(BizExceptionDetail record);

    /**
     *对非空字段更新
     */
    int updateByPrimaryKey(BizExceptionDetail record);

    /**
     *通过主键字段删除
     */
    int deleteByPrimaryKey(Long id);

    /**
     *通过主键字段查询
     */
    BizExceptionDetail selectByPrimaryKey(Long id);

    /**
     *获取数据
     */
    List<BizExceptionDetail> selectAllByMap(Map map);}
