package com.example.nacos_client.service.imp;

import com.example.nacos_client.elasticsearch.document.EsProduct;
import com.example.nacos_client.elasticsearch.repository.EsProductRepository;
import com.example.nacos_client.elasticsearch.repository.SysDepartmentRepository;
import com.example.nacos_client.mapper.SysDepartmentMapper;
import com.example.nacos_client.pojo.SysDepartment;
import com.example.nacos_client.service.ISysDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Iterator;
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
    @Autowired
    private SysDepartmentRepository sysDepartmentRepository;
    /**
     *获取序列值
     */
    @Override
    public Long getNextVal() throws Exception{
        return sysDepartmentMapper.getNextVal();
    }

    @Override
    public int importAll() {
        List<SysDepartment> esProductList = sysDepartmentMapper.selectAllByMap(null);
        Iterable<SysDepartment> esProductIterable = sysDepartmentRepository.saveAll(esProductList);
        Iterator<SysDepartment> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
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

    @Override
    public Page<SysDepartment> search(String name, Long id,String type,Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return sysDepartmentRepository.findByNameOrIdOrType(name, id, type, pageable);
    }

}
