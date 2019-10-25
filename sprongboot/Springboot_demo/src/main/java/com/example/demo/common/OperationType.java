package com.example.demo.common;

/**
 * @description:
 * @Author: liaoph
 * @Date: 2019/1/9 10:25
 * @Version 1.0
 */
public enum OperationType {
    // 1 登录 2 退出 3读取(查询) 4写入(新增) 5修改 6删除 7其他
    LOGIN("1"),
    EXIT("2"),
    QUERY("3"),
    ADD("4"),
    UPDATE("5"),
    DEL("6"),
    OTHER("7");

    String code;
    OperationType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
