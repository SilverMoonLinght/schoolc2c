package com.schoolc2c.trade.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.service.ProductWantedService;
import com.schoolc2c.trade.mapper.ProductWantedMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductWantedServiceImpl implements ProductWantedService {

    private int flag;

    @Autowired
    ProductWantedMapper productWantedMapper;

    @Override
    public String editProductWanted(ProductWanted productWanted) {

        flag = productWantedMapper.updateByPrimaryKey(productWanted);
        if (flag>0){
            return "success";
        }

        return "fail";
    }

    @Override
    public String removeProductWanted(ProductWanted productWanted) {

        flag = productWantedMapper.delete(productWanted);
        if (flag>0){
            return "success";
        }

        return "fail";
    }
}
