package com.example.demo.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface helloMapper {
   //@Select("select * from sys_code where rownum<11")
    List<Map> getlist();
}
