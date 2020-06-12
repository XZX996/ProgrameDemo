package com.example.nacos_client.mapper;

import com.example.nacos_client.pojo.SysDepartment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门信息表Mapper
 * xzx
 * 2020-06-11
 */

public interface SysDepartmentMapper {
    /**
     *全字段插入
     */
    int insert(SysDepartment record);

    /**
     *对非空字段插入
     */
    int insertSelective(SysDepartment record);

    /**
     *全字段更新
     */
    int updateByPrimaryKeySelective(SysDepartment record);

    /**
     *对非空字段更新
     */
    int updateByPrimaryKey(SysDepartment record);

    /**
     *通过主键字段删除
     */
    int deleteByPrimaryKey(Long id);

    /**
     *通过主键字段查询
     */
    SysDepartment selectByPrimaryKey(Long id);

    /**
     *获取序列值
     */
    Long getNextVal();

    /**
     *获取数据
     */
    List<SysDepartment> selectAllByMap(Map map);}
