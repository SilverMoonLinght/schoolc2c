package com.schoolc2c.trade.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.GetProductInfoService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetProductInfoServiceImpl implements GetProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;


    @Override
    public ProductInfo getProductInfo(String id) {

        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);

        return productInfo;
    }
}
