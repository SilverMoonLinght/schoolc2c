package com.schoolc2c.trade.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.schoolc2c.bean.ProductInfo;

import com.schoolc2c.service.ProductInfoService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import com.schoolc2c.trade.mapper.UserMapper;
import com.schoolc2c.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    private int flag;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public ProductInfo getProductInfo(String id) {

        Jedis jedis = redisUtil.getJedis();

        String productJson = jedis.get("product:" + id + "info");
        ProductInfo productInfo = new ProductInfo();

        if (StringUtils.isNotBlank(productJson)){
             productInfo = JSON.parseObject(productJson, ProductInfo.class);
        }else {
             productInfo = productInfoMapper.selectByPrimaryKey(id);
             if (productInfo != null){
                 jedis.set("product:"+id+"info",JSON.toJSONString(productInfo));
             }
        }
        jedis.close();

        productInfo.setSchool(userMapper.selectByPrimaryKey(productInfo.getUserId()).getSchool());

        return productInfo;
    }

    @Override
    public String editProductInfo(ProductInfo productInfo) {

        flag = productInfoMapper.updateByPrimaryKey(productInfo);

        if (flag > 0) {
            return "success";
        }

        return "fail";
    }

    @Override
    public String removeProduct(ProductInfo productInfo) {

        flag = productInfoMapper.delete(productInfo);

        if (flag > 0) {
            return "success";
        }


        return "fail";
    }
}
