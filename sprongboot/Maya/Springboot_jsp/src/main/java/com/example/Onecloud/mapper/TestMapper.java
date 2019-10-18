package com.example.Onecloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.Onecloud.pojo.TestDto;


public interface TestMapper extends BaseMapper<TestDto> {

  /*  @Select("select * from sys_code where rownum<11")
    List<Map> getlist();*/

}
