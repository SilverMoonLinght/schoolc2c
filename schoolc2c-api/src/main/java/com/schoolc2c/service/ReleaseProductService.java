package com.schoolc2c.service;

import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;

public interface ReleaseProductService {
    int addProductInfo(ProductInfo productInfo);

    String addProductWanted(ProductWanted productWanted);

    Pages getProductWantedList(int pageNum, int pageSize);
}
