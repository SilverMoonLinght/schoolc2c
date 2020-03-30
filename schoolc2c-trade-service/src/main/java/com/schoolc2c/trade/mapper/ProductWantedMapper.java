package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.ProductWanted;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductWantedMapper extends Mapper<ProductWanted> {
    List<ProductWanted> selectPageProductWantedLsit(@Param("startNum") int startNum, @Param("pageSize") int pageSize);
}
