package com.schoolc2c.trade.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Auction;
import com.schoolc2c.bean.AuctionRecord;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.service.AuctionService;
import com.schoolc2c.trade.mapper.AuctionMapper;
import com.schoolc2c.trade.mapper.AuctionRecordMapper;
import com.schoolc2c.trade.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionMapper auctionMapper;
    @Autowired
    AuctionRecordMapper auctionRecordMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public String releaseAuction(Auction auction) {

        int flag = auctionMapper.insert(auction);

        if (flag>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public Pages getAuctionList(String catalog3Id, int pageNum, int pageSize) {

        Pages pages=new Pages();
        int startNum=(pageNum-1)*pageSize;
        if (catalog3Id ==null){
            pages.setAuctions(auctionMapper.selectPageAuction(startNum,pageSize));
            List<Auction> auctionList = auctionMapper.selectAll();
            pages.setTotalPageNum(auctionList.size());
        }

        return pages;
    }

    @Override
    public Auction getAuctionInfo(String id) {

        Auction auction = auctionMapper.selectByPrimaryKey(id);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        auction.setStartdate(simpleDateFormat.format(auction.getStarttime()));
        auction.setEnddate(simpleDateFormat.format(auction.getEndtime()));

        return auction;
    }

    @Override
    public String submitAuctionPrice(AuctionRecord auctionRecord) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            auctionRecord.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int flag = auctionRecordMapper.insert(auctionRecord);
        if (flag>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public List<AuctionRecord> getAuctionRecord(String aid) {

        List<AuctionRecord> auctionRecords = auctionRecordMapper.selectAuctionRecordOrderByAid(aid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i=0;i<auctionRecords.size();i++){
            auctionRecords.get(i).setDatetime(simpleDateFormat.format(auctionRecords.get(i).getTime()));
            auctionRecords.get(i).setUsername(userMapper.selectByPrimaryKey(auctionRecords.get(i).getUid()).getUsername());
        }

        return auctionRecords;
    }

    @Override
    public String getMaxPrice(String aid) {
        return auctionRecordMapper.selectMaxPriceByAid(aid);
    }
}
