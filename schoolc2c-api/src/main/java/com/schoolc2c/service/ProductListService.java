package com.schoolc2c.service;

import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;

import java.util.List;

public interface ProductListService {
    Pages getProductList(String catalog3Id, int pageNum, int pageSize);

    List<ProductInfo> getProductListByUser(String id);

    List<ProductWanted> getProductWantedByUser(String id);

    List<ProductInfo> getHotProduct();

    List<ProductInfo> getAllProduct();
}
