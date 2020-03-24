package com.schoolc2c.admin.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.AddCatalogForm;
import com.schoolc2c.bean.CatalogForm;
import com.schoolc2c.bean.ProductCatalog1;
import com.schoolc2c.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class CatalogController {

    @Reference
    CatalogService catalogService;


    //获取商品分类列表
    @RequestMapping("getCatalogList")
    @ResponseBody
    public List<ProductCatalog1> getCatalogList(){

        List<ProductCatalog1> productCatalog1List = catalogService.getCatalogList();
        return productCatalog1List;
    }
    //获取一二级分类列表
    @RequestMapping("getParentCatalogList")
    @ResponseBody
    public List<ProductCatalog1> getParentCatalogList(){

        List<ProductCatalog1> productCatalog1s = catalogService.getParentCatalogList();
        return productCatalog1s;
    }

    //添加分类
    @RequestMapping("addCatalog")
    @ResponseBody
    public int addCatalog(@RequestBody AddCatalogForm addCatalogForm){

        int status = catalogService.addCatalog(addCatalogForm);

        return status;
    }

    //删除分类
    @RequestMapping("deleteCatalog")
    @ResponseBody
    public int deleteCatalog(@RequestBody CatalogForm catalogForm){


        int status = catalogService.deleteCatalog(catalogForm);

        return status;
    }

    //修改分类
    @RequestMapping("editCatalog")
    @ResponseBody
    public int editCatalog(@RequestBody CatalogForm catalogForm){

        int status = catalogService.editCatalog(catalogForm);

        return status;
    }
}
