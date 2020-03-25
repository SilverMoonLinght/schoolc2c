package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.ReleaseProductService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ReleaseProductServiceImpl implements ReleaseProductService {

    @Autowired
    ProductInfoMapper productInfoMapper;


    @Override
    public int addProductInfo(ProductInfo productInfo) {

        int flag = productInfoMapper.insert(productInfo);

        if (flag>0){
            return 200;
        }else {
            return 500;
        }
    }
}
