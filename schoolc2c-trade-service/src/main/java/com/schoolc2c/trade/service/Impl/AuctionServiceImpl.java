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
    private int flag;


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
        }else {
            pages.setAuctions(auctionMapper.selectPartPageAuction(catalog3Id,startNum,pageSize));
        }

        return pages;
    }

    @Override
    public Auction getAuctionInfo(String id) {

        Auction auction = auctionMapper.selectByPrimaryKey(id);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        auction.setStartdate(simpleDateFormat.format(auction.getStarttime()));
        auction.setEnddate(simpleDateFormat.format(auction.getEndtime()));
        auction.setSchool(userMapper.selectByPrimaryKey(auction.getUserId()).getSchool());

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
            auctionRecords.get(i).setNickname(userMapper.selectByPrimaryKey(auctionRecords.get(i).getUid()).getNickname());
        }

        return auctionRecords;
    }

    @Override
    public String getMaxPrice(String aid) {
        return auctionRecordMapper.selectMaxPriceByAid(aid);
    }

    @Override
    public List<Auction> getAuctionByUser(String id) {

        Auction auction = new Auction();

        auction.setUserId(id);

        return auctionMapper.select(auction);
    }

    @Override
    public String editAuction(Auction auction) {

        flag = auctionMapper.updateByPrimaryKey(auction);

        if (flag>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String removeAuction(Auction auction) {

        AuctionRecord auctionRecord = new AuctionRecord();
        auctionRecord.setAid(auction.getId());
        flag =auctionMapper.delete(auction);

        if (flag>0){
            flag = auctionRecordMapper.delete(auctionRecord);
        }

        if (flag>=0){
            return "success";
        }

        return "fail";
    }

    @Override
    public List<Auction> getAuctionRecordByUser(String id) {


        List<String> ids = auctionRecordMapper.selectAuctionRecordByUser(id);
        List<Auction> auctionList = auctionMapper.selectAuctionByListId(ids);

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0;i<auctionList.size();i++){
            auctionList.get(i).setEnddate(simpleDateFormat.format(auctionList.get(i).getEndtime()));
        }

        return auctionList;
    }
}
