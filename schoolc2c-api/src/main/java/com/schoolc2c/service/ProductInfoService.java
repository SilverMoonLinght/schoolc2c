package com.schoolc2c.service;

import com.schoolc2c.bean.ProductInfo;

public interface ProductInfoService {

    ProductInfo getProductInfo(String id);

    String editProductInfo(ProductInfo productInfo);

    String removeProduct(ProductInfo productInfo);
}
