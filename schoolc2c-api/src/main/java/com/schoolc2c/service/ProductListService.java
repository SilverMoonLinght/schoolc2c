package com.schoolc2c.service;

import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;

import java.util.List;

public interface ProductListService {
    Pages getProductList(String catalog3Id, int pageNum, int pageSize);

    List<ProductInfo> getProductListByUser(String id);
}
