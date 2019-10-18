package com.example.demo.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * 异常详情
 * lxw
 * 2019-07-25
 */
public class BizExceptionDetail {
   /**
     * ID
     */
    private Long id;
 
   /**
     * 文书类别
     */
    private String writType;
 
   /**
     * 来源表
     */
    private String sourceTable;
 
   /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ctime;
 
   /**
     * 来源表主键字段集合
     */
    private String sourceTableKeys;
 
   /**
     * 来源表主键字段集合对应的值
     */
    private String sourceTableKeysValues;
 
   /**
     * 异常内容
     */
    private String content;
 
   /**
     * BIZ_EXCEPTION表主键
     */
    private Long eid;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getWritType() {
        return writType;
    }
 
    public void setWritType(String writType) {
        this.writType = writType;
    }
 
    public String getSourceTable() {
        return sourceTable;
    }
 
    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }
 
    public Date getCtime() {
        return ctime;
    }
 
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
 
    public String getSourceTableKeys() {
        return sourceTableKeys;
    }
 
    public void setSourceTableKeys(String sourceTableKeys) {
        this.sourceTableKeys = sourceTableKeys;
    }
 
    public String getSourceTableKeysValues() {
        return sourceTableKeysValues;
    }
 
    public void setSourceTableKeysValues(String sourceTableKeysValues) {
        this.sourceTableKeysValues = sourceTableKeysValues;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
 
    public Long getEid() {
        return eid;
    }
 
    public void setEid(Long eid) {
        this.eid = eid;
    }
 
}
