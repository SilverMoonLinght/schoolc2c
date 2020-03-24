package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.ProductCatalog2;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductCatalog2Mapper extends Mapper<ProductCatalog2> {
    List<ProductCatalog2> selectCatalog2ByPid(String catalog1Id);
}
