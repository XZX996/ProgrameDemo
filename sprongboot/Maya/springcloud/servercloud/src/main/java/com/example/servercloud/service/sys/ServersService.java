/*
package com.example.servercloud.service.sys;

import com.szdt.adminserver.po.common.Response;
import com.szdt.adminserver.po.sys.Servers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

*/
/**
 * @description:服务器
 * @version:1.0
 * @author:武伟
 * @date:2018/11/6
 *//*

public interface ServersService {

    */
/**
     * 初始化、刷新
     *//*

    void init();

    */
/**
     * 根据服务器名称字符串
     *
     * @param name http://localhost:9091/
     * @return {name:"",sysAccessPassword:"",ip:"",port:"",descr:""}
     *//*

    Map getServer(String name);

    */
/**
     * 根据rul查询server
     * @param url
     * @return
     *//*

    Map findServer(String url);

    */
/**
     * 服务器注册
     *
     * @return
     *//*

    public String serverRegist(HttpServletRequest request);

    */
/**
     * 转发请求
     * @param request
     * @param response
     *//*

    public void sendRedirect(HttpServletRequest request, HttpServletResponse response);

    */
/**
     * 获取所有服务
     * @return
     *//*

    List<Map> getallServers(Map map);

    */
/**
     * 删除服务
     * @param  ID
     * @return
     *//*

    Response deleteServers(Integer ID);

    Response addServers(Servers ser);
    Response updateServers(Servers ser);

}
*/
