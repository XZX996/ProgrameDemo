package com.example.demo.Dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userMapper {

    //登录验证
    String  getPassword(@Param("name") String name);
    //获取角色
    String getRole(@Param("name") String name);
    //获取当前登录用户菜单
    List<String> getMenus(@Param("loginName") String loginName);

    //获取所有菜单
    List<String> getAllMenus();

}
