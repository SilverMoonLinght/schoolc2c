package com.schoolc2c.trade.mapper;

import com.schoolc2c.bean.AuctionRecord;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuctionRecordMapper extends Mapper<AuctionRecord> {
    List<AuctionRecord> selectAuctionRecordOrderByAid(String aid);

    String selectMaxPriceByAid(String aid);

    List<String> selectAuctionRecordByUser(String id);
}
