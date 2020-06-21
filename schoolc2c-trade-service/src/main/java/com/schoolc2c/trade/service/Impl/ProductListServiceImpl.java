package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.HotProduct;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.service.ProductListService;
import com.schoolc2c.trade.mapper.HotProductMapper;
import com.schoolc2c.trade.mapper.MessageMapper;
import com.schoolc2c.trade.mapper.ProductInfoMapper;
import com.schoolc2c.trade.mapper.ProductWantedMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class ProductListServiceImpl implements ProductListService {



    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductWantedMapper productWantedMapper;

    @Autowired
    HotProductMapper hotProductMapper;

    @Autowired
    MessageMapper messageMapper;

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

    @Override
    public List<ProductWanted> getProductWantedByUser(String id) {

        ProductWanted productWanted = new ProductWanted();
        productWanted.setUserId(id);


        return productWantedMapper.select(productWanted);
    }


    @Override
    public List<ProductInfo> getHotProduct() {

        List<HotProduct> hotProducts = hotProductMapper.selectAll();
        List<ProductInfo> productInfoList = productInfoMapper.selectByHotProductId(hotProducts);

        return productInfoList;
    }

    @Override
    public List<ProductInfo> getAllProduct() {
        return productInfoMapper.selectAll();
    }


}
