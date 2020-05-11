package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.HotProduct;
import com.schoolc2c.bean.ProductInfo;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ProductInfoMapper extends Mapper<ProductInfo> {

    List<ProductInfo> selectPageProductInfo(@Param("startNum")int startNum, @Param("pageSize")int pageSize);

    List<ProductInfo> selectPartPageProductInfo(@Param("catalog3Id")String catalog3Id,@Param("startNum")int startNum,@Param("pageSize")int pageSize);

    List<ProductInfo> selectByHotProductId(List<HotProduct> hotProducts);
}
