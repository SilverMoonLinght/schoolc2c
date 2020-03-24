package com.schoolc2c.service;

import com.schoolc2c.bean.ProductCatalog1;
import com.schoolc2c.bean.ProductCatalog2;
import com.schoolc2c.bean.ProductCatalog3;

import java.util.List;

public interface GetCatalogService {
    List<ProductCatalog1> getCatalog1();

    List<ProductCatalog2> getCatalog2(String catalog1Id);

    List<ProductCatalog3> getCatalog3(String catalog2Id);

    List<ProductCatalog1> getCatalogList();
}
