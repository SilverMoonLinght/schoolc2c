package com.schoolc2c.trade.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.ProductInfo;

import com.schoolc2c.service.ProductInfoService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    private int flag;

    @Autowired
    ProductInfoMapper productInfoMapper;


    @Override
    public ProductInfo getProductInfo(String id) {

        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);

        return productInfo;
    }

    @Override
    public String editProductInfo(ProductInfo productInfo) {

         flag = productInfoMapper.updateByPrimaryKey(productInfo);

        if (flag>0){
            return "success";
        }

        return "fail";
    }

    @Override
    public String removeProduct(ProductInfo productInfo) {

        flag = productInfoMapper.delete(productInfo);

        if (flag>0){
            return "success";
        }


        return "fail";
    }
}
