package com.example.nacos_client.service.imp;

import com.example.nacos_client.Mapper.SysDepartmentMapper;
import com.example.nacos_client.pojo.SysDepartment;
import com.example.nacos_client.service.ISysDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 部门信息表ServiceImpl
 * xzx
 * 2020-06-11
 */
 @Service
public  class SysDepartmentServiceImpl implements ISysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;


    /**
     *获取序列值
     */
    @Override
    public Long getNextVal() throws Exception{
        return sysDepartmentMapper.getNextVal();
    }

    /**
     *新增
     */
    @Override
    public void add(SysDepartment record) throws Exception {
        sysDepartmentMapper.insertSelective(record);
    }

    /**
     *修改
     */
    @Override
    public void update(SysDepartment record) throws Exception {
        sysDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *删除
     */
    @Override
    public void delete(Long id) throws Exception {
        sysDepartmentMapper.deleteByPrimaryKey(id);
    }

    /**
     *获取实体
     */
    @Override
    public SysDepartment getModel(Long id) throws Exception {
        return sysDepartmentMapper.selectByPrimaryKey(id);
    }

    /**
     *获取列表
     */
    @Override
    public List<SysDepartment> selectAll(Map map) throws Exception {
        return sysDepartmentMapper.selectAllByMap(map);
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
        List<SysDepartment> list = sysDepartmentMapper.selectAllByMap(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
