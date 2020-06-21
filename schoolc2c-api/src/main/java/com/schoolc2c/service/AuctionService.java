package com.schoolc2c.service;

import com.schoolc2c.bean.Auction;
import com.schoolc2c.bean.AuctionRecord;
import com.schoolc2c.bean.Pages;

import java.util.List;

public interface AuctionService {
    String releaseAuction(Auction auction);

    Pages getAuctionList(String catalog3Id, int pageNum, int pageSize);

    Auction getAuctionInfo(String id);

    String submitAuctionPrice(AuctionRecord auctionRecord);

    List<AuctionRecord> getAuctionRecord(String aid);

    String getMaxPrice(String aid);

    List<Auction> getAuctionByUser(String id);

    String editAuction(Auction auction);

    String removeAuction(Auction auction);

    List<Auction> getAuctionRecordByUser(String id);
}
