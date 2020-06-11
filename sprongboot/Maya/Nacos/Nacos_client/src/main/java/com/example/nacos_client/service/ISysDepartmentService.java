package com.example.nacos_client.service;

import com.example.nacos_client.pojo.SysDepartment;
import com.github.pagehelper.PageInfo;
import java.util.Map;
import java.util.List;

/**
 * 部门信息表Service
 * xzx
 * 2020-06-11
 */
public interface ISysDepartmentService {

    /**
     * 新增
     */
    void add(SysDepartment record) throws Exception;

    /**
     * 修改
     */
    void update(SysDepartment record) throws Exception;

    /**
     * 删除
     */
    void delete(Long id) throws Exception;

    /**
     * 获取分页数据
     */
    PageInfo getList(Map map) throws Exception;

    /**
     *获取序列值
     */
    Long getNextVal() throws Exception;

    /**
     * 获取列表数据
     */
    List<SysDepartment> selectAll(Map map) throws Exception;

    /**
     * 获取实体
     */
    SysDepartment getModel(Long id) throws Exception;
    
}
