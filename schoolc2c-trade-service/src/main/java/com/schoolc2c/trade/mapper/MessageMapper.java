package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.HotProduct;
import com.schoolc2c.bean.Message;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageMapper extends Mapper<Message> {
    List<HotProduct> selectHotProductList();
}
