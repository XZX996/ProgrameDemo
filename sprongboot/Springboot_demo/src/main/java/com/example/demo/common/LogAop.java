package com.example.demo.common;


import com.alibaba.fastjson.JSON;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Aspect
@Component
public class LogAop {

    // 配置切点 及要传的参数
    //@Pointcut("execution(* com.example.demo.web.*.*(..))")
    @Pointcut("@annotation( com.example.demo.common.OperationLog)")
    public void pointCut()
    {
        System.out.println("执行的切面主程序");
    }


    // 配置连接点 方法开始执行时通知
    @Before("pointCut()")
    public void beforeLog(JoinPoint joinPoint) {
        //获取目标方法参数信息
        Object[] args = joinPoint.getArgs();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();

        OperationLog myLog = method.getAnnotation(OperationLog.class);
         //序列化时过滤掉request和response

        for(Object i:args){
            if(i instanceof HttpServletRequest){
                String IP= Iphelper.getIpAddr((HttpServletRequest) i);
                System.out.println(IP);
            }else {
                //将参数所在的数组转换成json
                String params = JSON.toJSONString(i);
            }
        }
        System.out.println("开始执行前置通知  日志记录:");
    }
    //    方法执行完后通知
    @After(value = "pointCut()")
    public void afterLog(JoinPoint joinPoint) {
        System.out.println("开始执行后置通知 日志记录:"+joinPoint);
    }
    //    执行成功后通知
    @AfterReturning(value = "pointCut()",returning = "result")
    public void afterReturningLog(JoinPoint joinPoint,Object result) {
        System.out.println("方法成功执行后通知 日志记录:"+result);
    }
    //    抛出异常后通知
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void afterThrowingLog(JoinPoint joinPoint,Throwable exception) {
        System.out.println("方法抛出异常后执行通知 日志记录"+exception);
    }

    /*//    环绕通知
    @Around("pointCut(id)")
    public Object aroundLog(ProceedingJoinPoint joinpoint,int id) {
        Object result = null;
        try {
            System.out.println("环绕通知开始 日志记录"+id);
            long start = System.currentTimeMillis();

            //有返回参数 则需返回值
            result =  joinpoint.proceed();

            long end = System.currentTimeMillis();
            System.out.println("总共执行时长" + (end - start) + " 毫秒");
            System.out.println("环绕通知结束 日志记录");
        } catch (Throwable t) {
            System.out.println("出现错误");
        }
        return result;
    }*/
}
