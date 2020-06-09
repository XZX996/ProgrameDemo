package com.example.nacos_server.filter;



import com.example.nacos_server.common.Response;
import com.example.nacos_server.common.ResponseUtil;
import com.example.nacos_server.common.ResultCodeEnum;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;


@Component
public class ZuulErrorFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ZuulErrorFilter.class);

    @Override
    public String filterType() {
        return "error";//异常过滤器
    }

    @Override
    public int filterOrder() {
        return 0;  //优先级，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true; //是否执行该过滤器，true代表需要过滤
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            ZuulException exception = this.findZuulException(context.getThrowable());
            logger.info("进入Zuul异常拦截");
            HttpServletResponse response = context.getResponse();
            response.setStatus(HttpServletResponse.SC_OK);
            Response res = new Response(ResultCodeEnum.ServiceException);
            context.set("zuul-error-info", res);
            ResponseUtil.writeJson(response, res.toJsonString());
        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
            logger.info("Zuul异常拦截处理异常:" + var5.getMessage());
        }
        return null;
    }

    ZuulException findZuulException(Throwable throwable) {
        if (throwable.getCause() instanceof ZuulRuntimeException) {
            // this was a failure initiated by one of the local filters
            return (ZuulException) throwable.getCause().getCause();
        }
        if (throwable.getCause() instanceof ZuulException) {
            // wrapped zuul exception
            return (ZuulException) throwable.getCause();
        }
        if (throwable instanceof ZuulException) {
            // exception thrown by zuul lifecycle
            return (ZuulException) throwable;
        }
        // fallback, should never get here
        return new ZuulException(throwable, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
    }

}

