/*
package com.example.servercloud.serviceimpl.sys;

import com.szdt.adminserver.mapper.servers.ServersMapper;
import com.szdt.adminserver.po.common.Response;
import com.szdt.adminserver.po.sys.Servers;
import com.szdt.adminserver.service.sys.ServersService;
import com.szdt.adminserver.utils.HttpUtils;
import com.szdt.adminserver.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

*/
/**
 * @description:服务器
 * @version:1.0
 * @author:武伟
 * @date:2018/11/6
 *//*

@Service
public class ServersServiceImpl implements ServersService {

    */
/**
     * 服务器缓存
     * 保存已配置的服务器信息
     *//*

    private static final Map<String, Map> SERVERS = new HashMap();

    */
/**
     * 日志工具
     *//*

    private Logger logger = Logger.getLogger(ServersServiceImpl.class);

    */
/**
     * 服务器配置mapper
     *//*

    @Resource
    private ServersMapper serversMapper;

    */
/**
     * 服务器接收管理服务器通知地址
     *//*

    @Value("${sys.security.server.receiver}")
    private String sysSecurityServerReceiver;

    */
/**
     * 安全令牌字段
     *//*

    @Value("${sys.security.token-name}")
    private String sysSecurityTokenName;
    */
/**
     * 安全令牌
     *//*

    @Value("${sys.security.token}")
    private String sysSecurityToken;
    */
/**
     * 服务器名字段
     *//*

    @Value("${sys.security.server.name-field}")
    private String sysSecurityServerNameField;

    */
/**
     * 初始化、刷新
     * 1、加载服务器缓存
     * 2、通知服务器到管理服务器注册
     *//*

    @Override
    public void init() {
        loadServers();
        notificationSevers();
    }

    */
/**
     * 加载服务器到缓存
     *//*

    private void loadServers() {
        try {
            List<Map> list = serversMapper.listServers();
            for (Map map : list) {
                String host = new StringBuilder().append("http://")
                        .append(map.get("ip")).append(":").append(map.get("port"))
                        .append("/").toString();
                map.put("host", host);
                SERVERS.put((String) map.get("name"), map);
                logger.info("缓存服务器配置：" + map.get("name") + ":" + host);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    */
/**
     * 通知其它服务器注册
     *//*

    private void notificationSevers() {
        for (Map.Entry entry : SERVERS.entrySet()) {
            try {
                Map server = (Map) entry.getValue();
                String host = server.get("host").toString() + entry.getKey() + "/";
                logger.info("通知服务器注册:" + host);
                Map<String, String> headers = new HashMap<>();
                headers.put(sysSecurityTokenName, sysSecurityToken);
                String result = HttpUtils.post(host + sysSecurityServerReceiver, null, headers);
                JSONObject json = JSONObject.fromObject(result);
                if (json.getJSONObject("meta").getInt("code") != Response.Meta.SUCCESS.getCode()) {
                    logger.error("通知服务器失败:" + host);
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("通知服务器失败:" + e.getMessage(), e);
            }
        }
    }

    */
/**
     * 根据根据服务器名称字符串获取
     *
     * @param name http://localhost:9091/
     * @return
     *//*

    @Override
    public Map getServer(String name) {
        Map<String, String> server = SERVERS.get(name);
        if (server == null) {
            return null;
        }
        Map map = new HashMap(server.size());
        for (Map.Entry entry : server.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }


    */
/**
     * 根据rul查询server
     *
     * @param url
     * @return
     *//*

    @Override
    public Map findServer(String url) {
        for (Map.Entry entry : SERVERS.entrySet()) {
            if (!(url.indexOf(entry.getKey().toString()) > 0)) {
                continue;
            }
            Map<String, String> server = (Map) entry.getValue();
            Map map = new HashMap(server.size());
            for (Map.Entry serverEntry : server.entrySet()) {
                map.put(serverEntry.getKey(), serverEntry.getValue());
            }
            return map;
        }
        return null;
    }

    */
/**
     * 服务器注册
     *
     * @return
     *//*

    @Override
    public String serverRegist(HttpServletRequest request) {
        */
/**
         * key无效，不注册
         *//*

        String accessValue = request.getHeader(this.sysSecurityTokenName);
        if (!this.sysSecurityToken.equals(accessValue)) {
            return null;
        }

        */
/**
         * 服务器未配置
         *//*

        String name = request.getHeader(this.sysSecurityServerNameField);
        Map server = SERVERS.get(name);
        if (server == null) {
            return null;
        }

        String key = UUID.randomUUID().toString();
        server.put(this.sysSecurityTokenName, key);
        return key;
    }

    */
/**
     * 转发请求
     *
     * @param request
     * @param response
     *//*

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURL().toString();
        for (Map.Entry entry : SERVERS.entrySet()) {
            if (!(url.indexOf(entry.getKey().toString()) > 0)) {
                continue;
            }
            Map server = (Map) entry.getValue();
            String urlNew = server.get("host") + url.substring(url.indexOf(entry.getKey().toString()));

            Map bodys = request.getParameterMap();
            Map headers = new HashMap<>();
            headers.put(sysSecurityTokenName, server.get(sysSecurityTokenName).toString());
            try {
                String result = HttpUtils.post(urlNew, JSONObject.fromObject(bodys).toString(), headers);
                ResponseUtil.writeJson(response, JSONObject.fromObject(result));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ResponseUtil.writeJson(response, new Response().failure(Response.Meta.Err501).toString());
    }

    @Override
    public List<Map> getallServers(Map map) {
        try {
            List<Map> list = serversMapper.allServers(map);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response deleteServers(Integer ID){
        int count=0;
        try {
            count=serversMapper.deleteServers(ID);
        } catch (Exception e) {
            return new Response().failure(Response.Meta.Err500,e.getMessage());
        }
        return new Response().success(count);
    }

    @Override
    public Response addServers(Servers ser) {
        int count=0;
        try {
            count=serversMapper.addServers(ser);
        } catch (Exception e) {
            return new Response().failure(Response.Meta.Err500,e.getMessage());
        }
        return new Response().success(count);
    }

    @Override
    public Response updateServers(Servers ser) {
        int count=0;
        try {
            count=serversMapper.updateServers(ser);
        } catch (Exception e) {
            return new Response().failure(Response.Meta.Err500,e.getMessage());
        }
        return new Response().success(count);
    }
}
*/
