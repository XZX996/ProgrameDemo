package com.example.servercloud.mapper.servers;

import com.example.servercloud.pojo.service;
import com.sun.xml.internal.ws.wsdl.writer.document.Service;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ServersMapper {

    /**
     * 查询所有有效的微服务
     *
     * @return
     * @throws Exception
     */
    List listServers() throws Exception;

    /**
     * 查询所有服务
     * @return
     * @throws Exception
     */
    List allServers(Map map) throws Exception;

    int deleteServers(Integer ID) throws Exception;
    int addServers(service ser) throws Exception;
    int updateServers(service ser) throws Exception;



}
