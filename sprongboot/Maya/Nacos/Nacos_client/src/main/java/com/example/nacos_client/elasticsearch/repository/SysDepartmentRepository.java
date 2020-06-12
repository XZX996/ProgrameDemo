package com.example.nacos_client.elasticsearch.repository;

import com.example.nacos_client.elasticsearch.document.EsProduct;
import com.example.nacos_client.pojo.SysDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 部门ES操作类
 *
 */
public interface SysDepartmentRepository extends ElasticsearchRepository<SysDepartment, Long> {

    Page<SysDepartment> findByNameOrIdOrType(String name, Long id, String type, Pageable page);

}
