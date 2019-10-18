package com.example.Onecloud.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userMapper{

    //登录验证
    String  getPassword(@Param("name") String name);
    //获取角色
    String getRole(@Param("name") String name);
}
