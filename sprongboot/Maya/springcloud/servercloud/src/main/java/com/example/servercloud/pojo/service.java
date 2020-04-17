package com.example.servercloud.pojo;


import com.baomidou.mybatisplus.annotation.*;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 服务实体类 xzx
 */

@Data
@TableName(value ="SERVERS_BAK")
public class service extends Model<service> {
    /**
     * id主键
     */
    @TableId(value ="ID")
    private Long id;

    /**
     * ip
     */
    @TableField(value ="IP")
    private String ip;

    /**
     * 状态
     */
    @TableField(value ="STATE")
    private Long state;

    /**
     * 服务名
     */
    @TableField(value ="NAME")
    private String name;

    /**
     *端口
     */
    @TableField(value ="PORT")
    private String port;

    /**
     *描述
     */
    @TableField(value ="DESCR")

    private String descr;


    /**
     * 重试次数
     */
    @TableField(value ="RETRYABLE")

    private String retryable;

    /**
     * 路径
     */
    @TableField(value ="PATH")

    private String path;


}
