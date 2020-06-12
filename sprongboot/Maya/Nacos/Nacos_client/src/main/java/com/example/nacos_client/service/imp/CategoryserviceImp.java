package com.example.nacos_client.service.imp;

import com.example.nacos_client.elasticsearch.document.Category;
import com.example.nacos_client.elasticsearch.document.EsProduct;
import com.example.nacos_client.elasticsearch.repository.CategoryRepository;
import com.example.nacos_client.elasticsearch.repository.EsProductRepository;
import com.example.nacos_client.service.Categoryservice;
import org.elasticsearch.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CategoryserviceImp implements Categoryservice {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryserviceImp.class);
    @Autowired
    private CategoryRepository productRepository;

    @Override
    public int importAll() {
        int result = 0;
        /*List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();

        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }*/
        return result;
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Category create(Long id) {
        return null;
    }

    @Override
    public void delete(List<Long> ids) {

    }


    @Override
    public Page<Category> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrPriceOrCategory(keyword, keyword, keyword, pageable);
    }

}
