package com.example.demo.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface helloMapper {
   //@Select("select * from sys_code where rownum<11")
    @Cacheable(value = "Map")
    List<Map> getlist();
}
