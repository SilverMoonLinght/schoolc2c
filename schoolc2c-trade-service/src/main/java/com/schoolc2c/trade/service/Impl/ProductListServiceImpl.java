package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.ProductListService;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductListServiceImpl implements ProductListService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public Pages getProductList(String catalog3Id, int pageNum, int pageSize) {

        Pages pages=new Pages();
        int startNum=(pageNum-1)*pageSize;
        if (catalog3Id == null){
            pages.setProductInfoList(productInfoMapper.selectPageProductInfo(startNum,pageSize));
            List<ProductInfo> productInfoList = productInfoMapper.selectAll();
            pages.setTotalPageNum(productInfoList.size());

        }else {
            pages.setProductInfoList(productInfoMapper.selectPartPageProductInfo(catalog3Id,startNum,pageSize));
            ProductInfo productInfo = new ProductInfo();
            productInfo.setCatalog3Id(catalog3Id);
            List<ProductInfo> productInfoList = productInfoMapper.select(productInfo);
            pages.setTotalPageNum(productInfoList.size());
        }
        pages.setPageNum(pageNum);
        return pages;
    }

    @Override
    public List<ProductInfo> getProductListByUser(String id) {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setUserId(id);

        List<ProductInfo> productInfoList = productInfoMapper.select(productInfo);
        return productInfoList;
    }
}
