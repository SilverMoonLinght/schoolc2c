package com.schoolc2c.admin.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.*;
import com.schoolc2c.service.CatalogService;
import com.schoolc2c.admin.mapper.ProductCatalog1Mapper;
import com.schoolc2c.admin.mapper.ProductCatalog2Mapper;
import com.schoolc2c.admin.mapper.ProductCatalog3Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    ProductCatalog1Mapper productCatalog1Mapper;

    @Autowired
    ProductCatalog2Mapper productCatalog2Mapper;

    @Autowired
    ProductCatalog3Mapper productCatalog3Mapper;

    //获取商品分类列表
    @Override
    public List<ProductCatalog1> getCatalogList() {

        List<ProductCatalog1> productCatalog1 = productCatalog1Mapper.selectAll();

        for (int i=0;i<productCatalog1.size();i++){
            ProductCatalog2 productCatalog2=new ProductCatalog2();
            productCatalog2.setCatalog1Id(productCatalog1.get(i).getId());
            List<ProductCatalog2> productCatalog2s = productCatalog2Mapper.select(productCatalog2);
            productCatalog1.get(i).setCatalog(productCatalog2s);
            productCatalog1.get(i).setLevel(1);
            for (int j=0;j<productCatalog2s.size();j++){
                ProductCatalog3 productCatalog3 = new ProductCatalog3();
                productCatalog3.setCatalog2Id(productCatalog2s.get(j).getId());
                List<ProductCatalog3> productCatalog3s = productCatalog3Mapper.select(productCatalog3);
                productCatalog2s.get(j).setCatalog(productCatalog3s);
                productCatalog2s.get(j).setLevel(2);
                for (int x=0;x<productCatalog3s.size();x++){
                    productCatalog3s.get(x).setLevel(3);
                }
            }
        }
        return productCatalog1;
    }

    @Override
    public List<ProductCatalog1> getParentCatalogList() {

        List<ProductCatalog1> productCatalog1 = productCatalog1Mapper.selectAll();

        for (int i=0;i<productCatalog1.size();i++){
            ProductCatalog2 productCatalog2=new ProductCatalog2();
            productCatalog2.setCatalog1Id(productCatalog1.get(i).getId());
            List<ProductCatalog2> productCatalog2s = productCatalog2Mapper.select(productCatalog2);
            productCatalog1.get(i).setCatalog(productCatalog2s);
        }


        return productCatalog1;
    }

    //添加分类
    @Override
    public int addCatalog(AddCatalogForm addCatalogForm) {

        int flag;

        if (addCatalogForm.getLevel() == 1){
            ProductCatalog1 productCatalog1 = new ProductCatalog1();
            productCatalog1.setName(addCatalogForm.getName());
            flag=productCatalog1Mapper.insert(productCatalog1);
        }else if(addCatalogForm.getLevel() == 2){
            ProductCatalog2 productCatalog2 = new ProductCatalog2();
            productCatalog2.setCatalog1Id(addCatalogForm.getPid());
            productCatalog2.setName(addCatalogForm.getName());
            flag=productCatalog2Mapper.insert(productCatalog2);
        }else {
            ProductCatalog3 productCatalog3 = new ProductCatalog3();
            productCatalog3.setCatalog2Id(addCatalogForm.getPid());
            productCatalog3.setName(addCatalogForm.getName());
            flag=productCatalog3Mapper.insert(productCatalog3);
        }


        if (flag>0){
            return 200;
        }else {
            return 0;
        }


    }

    //删除分类
    @Override
    public int deleteCatalog(CatalogForm catalogForm) {

        int flag = 1;

        if (catalogForm.getLevel()==1){
            ProductCatalog1 productCatalog1 = new ProductCatalog1();
            productCatalog1.setId(catalogForm.getId());
            productCatalog1.setName(catalogForm.getName());
            if (catalogForm.getCatalog()!=null){

                List<ProductCatalog2> productCatalog2s = catalogForm.getCatalog();
                for (int i=0;i<productCatalog2s.size();i++){

                    if (productCatalog2s.get(i).getCatalog()!=null){
                        List<ProductCatalog3> productCatalog3s = productCatalog2s.get(i).getCatalog();
                        for (int j=0;j<productCatalog3s.size();j++){
                            flag = productCatalog3Mapper.delete(productCatalog3s.get(j));
                        }
                    }
                    if (flag>0){
                        flag = productCatalog2Mapper.delete(productCatalog2s.get(i));
                    }
                }
            }
            if (flag>0){
                flag=productCatalog1Mapper.delete(productCatalog1);
            }
        }else if (catalogForm.getLevel()==2){
            ProductCatalog2 productCatalog2 = new ProductCatalog2();
            productCatalog2.setId(catalogForm.getId());
            productCatalog2.setName(catalogForm.getName());
            if (catalogForm.getCatalog()!=null){
                for (int i = 0; i< catalogForm.getCatalog().size(); i++){
                    flag = productCatalog3Mapper.deleteByPrimaryKey(catalogForm.getCatalog().get(i));
                }
            }
            if (flag>0){
                flag = productCatalog2Mapper.delete(productCatalog2);
            }
        }else {
            ProductCatalog3 productCatalog3 = new ProductCatalog3();
            productCatalog3.setId(catalogForm.getId());
            productCatalog3.setName(catalogForm.getName());
            flag=productCatalog3Mapper.delete(productCatalog3);
        }


        if (flag>0){
            return 200;
        }else return 0;
    }

    //修改分类
    @Override
    public int editCatalog(CatalogForm catalogForm) {
        
        int flag = 0;
        
        if (catalogForm.getLevel() == 1){
            ProductCatalog1 productCatalog1 = new ProductCatalog1();
            productCatalog1.setId(catalogForm.getId());
            productCatalog1.setName(catalogForm.getName());

            flag = productCatalog1Mapper.updateByPrimaryKey(productCatalog1);

        }else if(catalogForm.getLevel() == 2){
            ProductCatalog2 productCatalog2 = new ProductCatalog2();
            productCatalog2.setId(catalogForm.getId());
            productCatalog2.setName(catalogForm.getName());
            productCatalog2.setCatalog1Id(catalogForm.getPid());

            flag = productCatalog2Mapper.updateByPrimaryKey(productCatalog2);
        }else {
            ProductCatalog3 productCatalog3 = new ProductCatalog3();
            productCatalog3.setId(catalogForm.getId());
            productCatalog3.setName(catalogForm.getName());
            productCatalog3.setCatalog2Id(catalogForm.getPid());

            flag = productCatalog3Mapper.updateByPrimaryKey(productCatalog3);
        }
        
        if (flag>0){
            return 200;
        }else {
            return 0;
        }

    }


}
