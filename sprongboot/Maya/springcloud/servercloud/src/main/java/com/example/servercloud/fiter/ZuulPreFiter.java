package com.example.servercloud.fiter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ZuulServlet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

@Component
public class ZuulPreFiter extends ZuulFilter {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
     * 另外还有“route”、“post”、"error”等类型
     * 这里定义为pre，代表会在请求被路由之前执行。
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序，通过数字指定。
     * 数字越大，优先级越低。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。
     * 实际运用中我们可以利用该函数来指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //SimpleHostRoutingFilter
        /*URL url= RequestContext.getCurrentContext().getRouteHost();
        if(url==null && ctx.sendZuulResponse()){
            return false;
        }*/
        //RibbonRoutingFilter
        /*if(ctx.getRouteHost() == null && ctx.get("serviceId") != null
                && ctx.sendZuulResponse()){
            return false;
        }*/

        String url1=request.getServletPath();
        if(url1.contains("eureka")){
            return false;
        }
        return true;
    }

    /**
     * 过滤器的具体逻辑
     *
     * @return
     */
    @Override
    public Object run() throws ZuulException {
        //获取请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        URL url = null;
        try {
            url = new URL(request.getRequestURL().toString());
            String  path = url.getPath();
            List<String> services = new ArrayList<>();
            List<String> serviceNames = discoveryClient.getServices();
            for(String serviceName : serviceNames){
                List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
                for(ServiceInstance serviceInstance : serviceInstances){
                    EurekaDiscoveryClient.EurekaServiceInstance aa=(EurekaDiscoveryClient.EurekaServiceInstance)serviceInstance;
                    services.add(String.format("%s:%s:%s:%s",serviceName,aa.getServiceId(),aa.getInstanceInfo().getIPAddr(),aa.getInstanceInfo().getPort()));
                }
            }
             // PreDecorationFilter
            // 根据requestURI获取路由信息
            String ipAddr=this.getIpAddr(request);
            System.out.println("请求IP地址为：[{}]"+ipAddr);
            //配置本地IP白名单，生产环境可放入数据库或者redis中
            List<String> ips=new ArrayList<String>();
            ips.add("172.0.0.1");


       /* if(!ips.contains(ipAddr)){
            System.out.println("IP地址校验不通过！！！");
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("IpAddr is forbidden!");
        }
        System.out.println("IP校验通过。");*/
       /* String token = request.getParameter("token");
        if (token == null || token.isEmpty()) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("token is empty");
        }*/
            return null;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    public  String getIpAddr(HttpServletRequest request){

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
