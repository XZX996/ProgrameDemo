package com.example.nacos_client.service;

import com.example.nacos_client.elasticsearch.document.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Categoryservice {
/*
     * 从数据库中导入所有商品到ES

*/
    int importAll();

/*
     * 根据id删除商品

*/
    void delete(Integer id);

/*
     * 根据id创建商品

*/
    Category create(Long id);

/*
     * 批量删除商品

*/
    void delete(List<Long> ids);

/*
     * 根据关键字搜索名称或者副标题

*/
    Page<Category> search(String keyword, Integer pageNum, Integer pageSize);

}
