package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.bean.User;
import com.schoolc2c.service.ReleaseProductService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import com.schoolc2c.trade.mapper.ProductWantedMapper;
import com.schoolc2c.trade.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ReleaseProductServiceImpl implements ReleaseProductService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductWantedMapper productWantedMapper;

    @Autowired
    UserMapper userMapper;


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

    @Override
    public Pages getProductWantedList(int pageNum, int pageSize) {

        Pages pages = new Pages();

        int startNum = (pageNum-1)*pageSize;

        List<ProductWanted> productWanteds = productWantedMapper.selectAll();
        pages.setTotalPageNum(productWanteds.size());
        pages.setProductWanteds(productWantedMapper.selectPageProductWantedLsit(startNum,pageSize));

        User user = new User();
        for (int i=0;i<pages.getProductWanteds().size();i++){
            user = userMapper.selectByPrimaryKey(pages.getProductWanteds().get(i).getUserId());
            if (user!=null){
                pages.getProductWanteds().get(i).setUsername(user.getUsername());
                pages.getProductWanteds().get(i).setIcon(user.getIcon());
                pages.getProductWanteds().get(i).setSchool(user.getSchool());
            }
        }
        pages.setPageNum(pageNum);
        return pages;
    }
}
