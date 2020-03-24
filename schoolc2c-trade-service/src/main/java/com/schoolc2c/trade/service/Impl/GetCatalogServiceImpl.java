package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.ProductCatalog1;
import com.schoolc2c.bean.ProductCatalog2;
import com.schoolc2c.bean.ProductCatalog3;
import com.schoolc2c.service.GetCatalogService;
import com.schoolc2c.trade.mapper.ProductCatalog1Mapper;
import com.schoolc2c.trade.mapper.ProductCatalog2Mapper;
import com.schoolc2c.trade.mapper.ProductCatalog3Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GetCatalogServiceImpl implements GetCatalogService {


    @Autowired
    ProductCatalog1Mapper productCatalog1Mapper;

    @Autowired
    ProductCatalog2Mapper productCatalog2Mapper;

    @Autowired
    ProductCatalog3Mapper productCatalog3Mapper;

    @Override
    public List<ProductCatalog1> getCatalog1() {

        List<ProductCatalog1> productCatalog1s = productCatalog1Mapper.selectAll();

        return  productCatalog1s;
    }

    @Override
    public List<ProductCatalog2> getCatalog2(String catalog1Id) {

        List<ProductCatalog2> productCatalog2s = productCatalog2Mapper.selectCatalog2ByPid(catalog1Id);

        return productCatalog2s;
    }

    @Override
    public List<ProductCatalog3> getCatalog3(String catalog2Id) {


        ProductCatalog3 productCatalog3 = new ProductCatalog3();
        productCatalog3.setCatalog2Id(catalog2Id);

        return productCatalog3Mapper.select(productCatalog3);
    }

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
}
