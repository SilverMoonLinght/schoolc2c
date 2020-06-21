package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.Auction;
import com.schoolc2c.bean.AuctionRecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuctionMapper extends Mapper<Auction> {
    List<Auction> selectPageAuction(@Param("startNum") int startNum,@Param("pageSize") int pageSize);

    List<Auction> selectAuctionByListId(List<String> ids);

    List<Auction> selectPartPageAuction(@Param("catalog3Id") String catalog3Id,@Param("startNum") int startNum,@Param("pageSize") int pageSize);
}
