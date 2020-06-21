package com.schoolc2c.auto.mapper;

import com.schoolc2c.bean.HotProduct;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HotProductMapper extends Mapper<HotProduct> {
    void deleteAll();

    void insertHotProducts(List<HotProduct> hotProducts);
}
