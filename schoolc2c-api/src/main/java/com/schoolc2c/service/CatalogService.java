package com.schoolc2c.service;

import com.schoolc2c.bean.AddCatalogForm;
import com.schoolc2c.bean.CatalogForm;
import com.schoolc2c.bean.ProductCatalog1;

import java.util.List;

public interface CatalogService {
    List<ProductCatalog1> getCatalogList();

    List<ProductCatalog1> getParentCatalogList();

    int addCatalog(AddCatalogForm addCatalogForm);

    int deleteCatalog(CatalogForm catalogForm);

    int editCatalog(CatalogForm catalogForm);
}
