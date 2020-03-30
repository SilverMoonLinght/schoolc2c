package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.service.ReleaseProductService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import com.schoolc2c.trade.mapper.ProductWantedMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ReleaseProductServiceImpl implements ReleaseProductService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductWantedMapper productWantedMapper;


    @Override
    public int addProductInfo(ProductInfo productInfo) {

        int flag = productInfoMapper.insert(productInfo);

        if (flag>0){
            return 200;
        }else {
            return 500;
        }
    }

    @Override
    public String addProductWanted(ProductWanted productWanted) {

        int flag = productWantedMapper.insert(productWanted);

        if (flag>0){
            return "success";
        }

        return "fail";
    }
}
