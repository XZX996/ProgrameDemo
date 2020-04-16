package com.example.servercloud.pojo;


import com.baomidou.mybatisplus.annotation.*;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 服务实体类 xzx
 */

@Data
@TableName(value ="SERVERS")
public class service extends Model<service> {
    /**
     *
     */
    @TableId(value ="ID")
    private Long id;

    /**
     *
     */
    @TableField(value ="IP")
    private String ip;

    /**
     *
     */
    @TableField(value ="STATE")
    private Long state;

    /**
     *
     */
    @TableField(value ="NAME")
    private String name;

    /**
     *
     */
    @TableField(value ="PORT")
    private String port;

    /**
     *
     */
    @TableField(value ="DESCR")

    private String descr;

}
