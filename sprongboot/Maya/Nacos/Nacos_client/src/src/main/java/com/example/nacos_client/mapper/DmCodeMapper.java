package com.example.nacos_client.mapper;

import com.example.nacos_client.pojo.DmCode;
import java.util.List;
import java.util.Map;

/**
 * Mapper
 * xzx
 * 2020-06-11
 */
public interface DmCodeMapper {
    /**
     *全字段插入
     */
    int insert(DmCode record);

    /**
     *对非空字段插入
     */
    int insertSelective(DmCode record);

    /**
     *全字段更新
     */
    int updateByPrimaryKeySelective(DmCode record);

    /**
     *对非空字段更新
     */
    int updateByPrimaryKey(DmCode record);

    /**
     *通过主键字段删除
     */
    int deleteByPrimaryKey(Long type);

    /**
     *通过主键字段查询
     */
    DmCode selectByPrimaryKey(Long type);

    /**
     *获取序列值
     */
    Long getNextVal();

    /**
     *获取数据
     */
    List<DmCode> selectAllByMap(Map map);}
