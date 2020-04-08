package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.Booth;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BoothMapper extends Mapper<Booth> {
    List<Booth> selectExceptBid(String bid);
}
