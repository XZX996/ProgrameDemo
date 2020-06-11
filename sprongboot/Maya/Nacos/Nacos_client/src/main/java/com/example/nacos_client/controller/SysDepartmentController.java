package com.example.nacos_client.controller;

import com.example.nacos_client.common.Response;
import com.example.nacos_client.pojo.SysDepartment;
import com.example.nacos_client.service.ISysDepartmentService;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 部门信息表Controller
 * xzx
 * 2020-06-11
 */
@RestController
@RequestMapping("/sysDepartment")
public  class SysDepartmentController {

    @Autowired
    private ISysDepartmentService sysDepartmentService;

    /**
     *获取分页数据
     */
    @PostMapping("/getData")
    public Response list(@RequestParam Map map) throws Exception {
        PageInfo pageInfo = sysDepartmentService.getList(map);
        return new Response().success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     *获取实体
     */
    @GetMapping("/getOne")
    public Response getOne(Long id) throws Exception {
        return new Response().success(sysDepartmentService.getModel(id));
    }

    /**
     *保存
     */
    @PostMapping("/add")
    public Response Save(@RequestBody SysDepartment record) throws Exception {
        if (null == record.getId()){
           return new Response().error("[id][主键标识]不能为空！");
        }
        if (null == record.getType()){
           return new Response().error("[type][部门类型  1]不能为空！");
        }
        if (null == record.getCzsj()){
           return new Response().error("[czsj][操作时间]不能为空，或者格式不正确，应为[yyyy-MM-dd HH:mm:ss]！");
        }
        if (null == record.getLxr()){
           return new Response().error("[lxr][联系人]不能为空！");
        }
        if (record.getLxr().length() > 60) {
            return new Response().error("[lxr][联系人]长度不能大于60！");
        }
        if (null == record.getParentId()){
           return new Response().error("[parentId][上级标识]不能为空！");
        }
        if (null == record.getCode()){
           return new Response().error("[code][机构代码]不能为空！");
        }
        if (record.getCode().length() > 50) {
            return new Response().error("[code][机构代码]长度不能大于50！");
        }
        if (null == record.getCzryzh()){
           return new Response().error("[czryzh][操作人员账号]不能为空！");
        }
        if (record.getCzryzh().length() > 30) {
            return new Response().error("[czryzh][操作人员账号]长度不能大于30！");
        }
        if (null == record.getIsVirtual()){
           return new Response().error("[isVirtual][是否虚拟节点]不能为空！");
        }
        if (null == record.getBmbh()){
           return new Response().error("[bmbh][部门编号]不能为空！");
        }
        if (record.getBmbh().length() > 12) {
            return new Response().error("[bmbh][部门编号]长度不能大于12！");
        }
        if (null == record.getName()){
           return new Response().error("[name][部门名称]不能为空！");
        }
        if (record.getName().length() > 128) {
            return new Response().error("[name][部门名称]长度不能大于128！");
        }
        if (null == record.getLxrdh()){
           return new Response().error("[lxrdh][联系人电话]不能为空！");
        }
        if (record.getLxrdh().length() > 30) {
            return new Response().error("[lxrdh][联系人电话]长度不能大于30！");
        }
        if (null == record.getZsbmbhs()){
           return new Response().error("[zsbmbhs][直属部门编号集合]不能为空！");
        }
        if (record.getZsbmbhs().length() > 128) {
            return new Response().error("[zsbmbhs][直属部门编号集合]长度不能大于128！");
        }
        if (null == record.getLxdz()){
           return new Response().error("[lxdz][联系地址]不能为空！");
        }
        if (record.getLxdz().length() > 256) {
            return new Response().error("[lxdz][联系地址]长度不能大于256！");
        }
        if (null == record.getLev()){
           return new Response().error("[lev][部门层级]不能为空！");
        }
        if (null == record.getFzjg()){
           return new Response().error("[fzjg][发证机关]不能为空！");
        }
        if (record.getFzjg().length() > 64) {
            return new Response().error("[fzjg][发证机关]长度不能大于64！");
        }
        if (null == record.getBz()){
           return new Response().error("[bz][备注]不能为空！");
        }
        if (record.getBz().length() > 1024) {
            return new Response().error("[bz][备注]长度不能大于1024！");
        }
        if (null == record.getSjzdbmbh()){
           return new Response().error("[sjzdbmbh][上级指导部门编号]不能为空！");
        }
        if (record.getSjzdbmbh().length() > 12) {
            return new Response().error("[sjzdbmbh][上级指导部门编号]长度不能大于12！");
        }
        if (null == record.getSfzsbm()){
           return new Response().error("[sfzsbm][是否直属部门  1]不能为空！");
        }
        if (null == record.getZt()){
           return new Response().error("[zt][状态   0]不能为空！");
        }
        if (null == record.getXzqh()){
           return new Response().error("[xzqh][行政区划（唯一）]不能为空！");
        }
        if (record.getXzqh().length() > 6) {
            return new Response().error("[xzqh][行政区划（唯一）]长度不能大于6！");
        }
        if (null == record.getFax()){
           return new Response().error("[fax][传真号码]不能为空！");
        }
        if (record.getFax().length() > 56) {
            return new Response().error("[fax][传真号码]长度不能大于56！");
        }
        sysDepartmentService.add(record);
        return new Response().success();
    }

    /**
     *修改
     */
    @PostMapping("/edit")
    public Response Edit(@RequestBody SysDepartment record) throws Exception {
        if (null == record.getId()){
           return new Response().error("[id][主键标识]不能为空！");
        }
        if (null == record.getType()){
           return new Response().error("[type][部门类型  1]不能为空！");
        }
        if (null == record.getCzsj()){
           return new Response().error("[czsj][操作时间]不能为空，或者格式不正确，应为[yyyy-MM-dd HH:mm:ss]！");
        }
        if (null == record.getLxr()){
           return new Response().error("[lxr][联系人]不能为空！");
        }
        if (record.getLxr().length() > 60) {
            return new Response().error("[lxr][联系人]长度不能大于60！");
        }
        if (null == record.getParentId()){
           return new Response().error("[parentId][上级标识]不能为空！");
        }
        if (null == record.getCode()){
           return new Response().error("[code][机构代码]不能为空！");
        }
        if (record.getCode().length() > 50) {
            return new Response().error("[code][机构代码]长度不能大于50！");
        }
        if (null == record.getCzryzh()){
           return new Response().error("[czryzh][操作人员账号]不能为空！");
        }
        if (record.getCzryzh().length() > 30) {
            return new Response().error("[czryzh][操作人员账号]长度不能大于30！");
        }
        if (null == record.getIsVirtual()){
           return new Response().error("[isVirtual][是否虚拟节点]不能为空！");
        }
        if (null == record.getBmbh()){
           return new Response().error("[bmbh][部门编号]不能为空！");
        }
        if (record.getBmbh().length() > 12) {
            return new Response().error("[bmbh][部门编号]长度不能大于12！");
        }
        if (null == record.getName()){
           return new Response().error("[name][部门名称]不能为空！");
        }
        if (record.getName().length() > 128) {
            return new Response().error("[name][部门名称]长度不能大于128！");
        }
        if (null == record.getLxrdh()){
           return new Response().error("[lxrdh][联系人电话]不能为空！");
        }
        if (record.getLxrdh().length() > 30) {
            return new Response().error("[lxrdh][联系人电话]长度不能大于30！");
        }
        if (null == record.getZsbmbhs()){
           return new Response().error("[zsbmbhs][直属部门编号集合]不能为空！");
        }
        if (record.getZsbmbhs().length() > 128) {
            return new Response().error("[zsbmbhs][直属部门编号集合]长度不能大于128！");
        }
        if (null == record.getLxdz()){
           return new Response().error("[lxdz][联系地址]不能为空！");
        }
        if (record.getLxdz().length() > 256) {
            return new Response().error("[lxdz][联系地址]长度不能大于256！");
        }
        if (null == record.getLev()){
           return new Response().error("[lev][部门层级]不能为空！");
        }
        if (null == record.getFzjg()){
           return new Response().error("[fzjg][发证机关]不能为空！");
        }
        if (record.getFzjg().length() > 64) {
            return new Response().error("[fzjg][发证机关]长度不能大于64！");
        }
        if (null == record.getBz()){
           return new Response().error("[bz][备注]不能为空！");
        }
        if (record.getBz().length() > 1024) {
            return new Response().error("[bz][备注]长度不能大于1024！");
        }
        if (null == record.getSjzdbmbh()){
           return new Response().error("[sjzdbmbh][上级指导部门编号]不能为空！");
        }
        if (record.getSjzdbmbh().length() > 12) {
            return new Response().error("[sjzdbmbh][上级指导部门编号]长度不能大于12！");
        }
        if (null == record.getSfzsbm()){
           return new Response().error("[sfzsbm][是否直属部门  1]不能为空！");
        }
        if (null == record.getZt()){
           return new Response().error("[zt][状态   0]不能为空！");
        }
        if (null == record.getXzqh()){
           return new Response().error("[xzqh][行政区划（唯一）]不能为空！");
        }
        if (record.getXzqh().length() > 6) {
            return new Response().error("[xzqh][行政区划（唯一）]长度不能大于6！");
        }
        if (null == record.getFax()){
           return new Response().error("[fax][传真号码]不能为空！");
        }
        if (record.getFax().length() > 56) {
            return new Response().error("[fax][传真号码]长度不能大于56！");
        }
        sysDepartmentService.update(record);
        return new Response().success();
    }

    /**
     *删除
     */
    @PostMapping("/delete")
    public Response remove(Long id) throws Exception {
        sysDepartmentService.delete(id);
        return new Response().success();
    }
}
