package com.example.nacos_client.controller;


import com.example.nacos_client.common.CommonPage;
import com.example.nacos_client.common.Response;
import com.example.nacos_client.elasticsearch.document.Category;
import com.example.nacos_client.elasticsearch.document.EsProduct;
import com.example.nacos_client.service.Categoryservice;
import com.example.nacos_client.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索商品管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "CategoryController", description = "搜索商品管理")
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private Categoryservice CategoryService;



    /*@ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }*/

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public Response search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<Category> esProductPage = CategoryService.search(keyword, pageNum, pageSize);
        return new Response().success(CommonPage.restPage(esProductPage));
    }

}
