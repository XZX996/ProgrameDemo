package com.example.nacos_client.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
//import io.swagger.annotations.ApiModelProperty;

/**
 * 部门信息表
 * xzx
 * 2020-06-11
 */
@Document(indexName = "mq1", type = "product",shards = 1,replicas = 0)
public class SysDepartment {
   /**
     * 主键标识
     */
    //@ApiModelProperty(value="主键标识")
    @Id
    private Long id;

   /**
     * 部门类型  1
     *注解作用：不分词，查ES的时候，就是精确查找。
    //@ApiModelProperty(value="部门类型  1")
    */
   @Field(type = FieldType.Keyword)
    private Long type;

   /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@ApiModelProperty(value="操作时间")
    private Date czsj;

   /**
     * 联系人
     */
    //@ApiModelProperty(value="联系人")
    private String lxr;

   /**
     * 上级标识
     */
    //@ApiModelProperty(value="上级标识")
    private Long parentId;

   /**
     * 机构代码
     */
    //@ApiModelProperty(value="机构代码")
    private String code;

   /**
     * 操作人员账号
     */
    //@ApiModelProperty(value="操作人员账号")
    private String czryzh;

   /**
     * 是否虚拟节点
     */
    //@ApiModelProperty(value="是否虚拟节点")
    private Long isVirtual;

   /**
     * 部门编号
     */
    //@ApiModelProperty(value="部门编号")
    private String bmbh;

   /**
     * 部门名称
     */
    //@ApiModelProperty(value="部门名称")
    private String name;

   /**
     * 联系人电话
     */
    //@ApiModelProperty(value="联系人电话")
    private String lxrdh;

   /**
     * 直属部门编号集合
     */
    //@ApiModelProperty(value="直属部门编号集合")
    private String zsbmbhs;

   /**
     * 联系地址
     */
    //@ApiModelProperty(value="联系地址")
    private String lxdz;

   /**
     * 部门层级
     */
    //@ApiModelProperty(value="部门层级")
    private Long lev;

   /**
     * 发证机关
     */
    //@ApiModelProperty(value="发证机关")
    private String fzjg;

   /**
     * 备注
     */
    //@ApiModelProperty(value="备注")
    private String bz;

   /**
     * 上级指导部门编号
     */
    //@ApiModelProperty(value="上级指导部门编号")
    private String sjzdbmbh;

   /**
     * 是否直属部门  1
     */
    //@ApiModelProperty(value="是否直属部门  1")
    private Long sfzsbm;

   /**
     * 状态   0
     */
    //@ApiModelProperty(value="状态   0")
    private Long zt;

   /**
     * 行政区划（唯一）
     */
    //@ApiModelProperty(value="行政区划（唯一）")
    private String xzqh;

   /**
     * 传真号码
     */
    //@ApiModelProperty(value="传真号码")
    private String fax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCzryzh() {
        return czryzh;
    }

    public void setCzryzh(String czryzh) {
        this.czryzh = czryzh;
    }

    public Long getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Long isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLxrdh() {
        return lxrdh;
    }

    public void setLxrdh(String lxrdh) {
        this.lxrdh = lxrdh;
    }

    public String getZsbmbhs() {
        return zsbmbhs;
    }

    public void setZsbmbhs(String zsbmbhs) {
        this.zsbmbhs = zsbmbhs;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public Long getLev() {
        return lev;
    }

    public void setLev(Long lev) {
        this.lev = lev;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSjzdbmbh() {
        return sjzdbmbh;
    }

    public void setSjzdbmbh(String sjzdbmbh) {
        this.sjzdbmbh = sjzdbmbh;
    }

    public Long getSfzsbm() {
        return sfzsbm;
    }

    public void setSfzsbm(Long sfzsbm) {
        this.sfzsbm = sfzsbm;
    }

    public Long getZt() {
        return zt;
    }

    public void setZt(Long zt) {
        this.zt = zt;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
