package com.example.demo.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * 异常类别
 * lxw
 * 2019-07-25
 */
public class BizExceptionType {
   /**
     * 异常类型代码
     */
    private String etype;
 
   /**
     * 是否参与考核  0不参与  1参与
     */
    private String isAssessment;
 
   /**
     * 描述
     */
    private String des;
 
   /**
     * 异常类型名称
     */
    private String name;
 
   /**
     * 状态
     */
    private String state;
 
   /**
     * 所属系统 秩序 10 事故 11 车管 12 架管   13
     */
    private String belongSystem;
 
    public String getEtype() {
        return etype;
    }
 
    public void setEtype(String etype) {
        this.etype = etype;
    }
 
    public String getIsAssessment() {
        return isAssessment;
    }
 
    public void setIsAssessment(String isAssessment) {
        this.isAssessment = isAssessment;
    }
 
    public String getDes() {
        return des;
    }
 
    public void setDes(String des) {
        this.des = des;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
 
    public String getBelongSystem() {
        return belongSystem;
    }
 
    public void setBelongSystem(String belongSystem) {
        this.belongSystem = belongSystem;
    }
 
}
