package com.example.demo.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("TEST")
public class TestDto {
    @TableId("ID")
    private int ID;
    @TableField("VAL")
    private String VAL;
    @TableField("TYPE")
    private String TYPE;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVAL() {
        return VAL;
    }

    public void setVAL(String VAL) {
        this.VAL = VAL;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
