package com.example.demo.common;

import java.lang.annotation.*;

/**
 * @description:自定义操作日志注解
 * @Author: liaoph
 * @Date: 2019/1/8 15:48
 * @Version 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface OperationLog {
    /**
     * 操作内容
     * @return
     */
    String value() default "";

    /**
     * 操作类型
     * 1 登录 2 退出 3读取(查询) 4写入(新增) 5修改 6删除 7其他
     * @return
     */
    OperationType[] type();

    /**
     * 系统代码
     * @return
     */
    String sysCode() default "00001";

    /**
     * 系统名称
     * @return
     */
    String sysName() default "";
}
