package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.Auction;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuctionMapper extends Mapper<Auction> {
    List<Auction> selectPageAuction(@Param("startNum") int startNum,@Param("pageSize") int pageSize);
}
