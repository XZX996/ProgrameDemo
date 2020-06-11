package com.example.nacos_client.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
//import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * xzx
 * 2020-06-11
 */
public class DmCode {
   /**
     * 
     */
    //@ApiModelProperty(value="")
    private Long type;
 
   /**
     * 
     */
    //@ApiModelProperty(value="")
    private String dm;
 
   /**
     * 
     */
    //@ApiModelProperty(value="")
    private String mc;
 
   /**
     * 顺序
     */
    //@ApiModelProperty(value="顺序")
    private Long sequence;
 
   /**
     * 
     */
    //@ApiModelProperty(value="")
    private Long status;
 
    public Long getType() {
        return type;
    }
 
    public void setType(Long type) {
        this.type = type;
    }
 
    public String getDm() {
        return dm;
    }
 
    public void setDm(String dm) {
        this.dm = dm;
    }
 
    public String getMc() {
        return mc;
    }
 
    public void setMc(String mc) {
        this.mc = mc;
    }
 
    public Long getSequence() {
        return sequence;
    }
 
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }
 
    public Long getStatus() {
        return status;
    }
 
    public void setStatus(Long status) {
        this.status = status;
    }
 
}
