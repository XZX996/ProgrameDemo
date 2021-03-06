package com.example.nacos_client.elasticsearch.repository;

import com.example.nacos_client.elasticsearch.document.Category;
import com.example.nacos_client.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/*
 * 商品ES操作类
 *

*/
public interface CategoryRepository extends ElasticsearchRepository<Category, Integer> {

    Page<Category> findByNameOrPriceOrCategory(String name, String price, String Category, Pageable page);

}
