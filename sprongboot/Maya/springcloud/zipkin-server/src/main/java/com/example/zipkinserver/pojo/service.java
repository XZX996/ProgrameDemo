package com.example.zipkinserver.pojo;



import lombok.Data;

/**
 * 服务实体类 xzx
 */

@Data
public class service{
    /**
     *
     */

    private Long id;

    /**
     *
     */
    private String ip;

    /**
     *
     */
    private Long state;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String port;

    /**
     *
     */
    private String descr;

}
