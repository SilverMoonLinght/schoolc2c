package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Booth;
import com.schoolc2c.bean.BoothProduct;
import com.schoolc2c.service.BoothService;
import com.schoolc2c.trade.mapper.BoothMapper;
import com.schoolc2c.trade.mapper.BoothProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class BoothServiceImpl implements BoothService {

    @Autowired
    BoothMapper boothMapper;

    @Autowired
    BoothProductMapper boothProductMapper;


    @Override
    public Booth getBoothInfo(String uid) {

        Booth booth =new Booth();
        booth.setUid(uid);

        booth = boothMapper.selectOne(booth);

        return booth;
    }

    @Override
    public String createBooth(String uid) {

        Booth booth =new Booth();
        booth.setUid(uid);

        int flag = boothMapper.insert(booth);
        if (flag>0){
            return "success";
        }

        return "fail";
    }

    @Override
    public List<BoothProduct> getBoothProductList(String bid) {
        BoothProduct boothProduct = new BoothProduct();
        boothProduct.setBid(bid);

        List<BoothProduct> boothProductList = boothProductMapper.select(boothProduct);

        if (boothProductList.size() == 0){
            return null;
        }
        return boothProductList;
    }

    @Override
    public String addBoothProduct(BoothProduct boothProduct) {

        int flag = boothProductMapper.insert(boothProduct);

        if (flag>0){
            return "success";
        }


        return "fail";
    }

    @Override
    public String editBoothProduct(BoothProduct boothProduct) {

        int flag = boothProductMapper.updateByPrimaryKey(boothProduct);
        if (flag>0){
            return "success";
        }

        return "fail";
    }

    @Override
    public String deleteBoothProduct(BoothProduct boothProduct) {

        int flag = boothProductMapper.delete(boothProduct);
        if (flag>0){
            return "success";
        }

        return "fail";
    }
}
