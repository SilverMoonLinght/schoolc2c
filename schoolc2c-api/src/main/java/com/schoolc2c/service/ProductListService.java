package com.schoolc2c.service;

import com.schoolc2c.bean.Pages;

public interface ProductListService {
    Pages getProductList(String catalog3Id, int pageNum, int pageSize);
}
