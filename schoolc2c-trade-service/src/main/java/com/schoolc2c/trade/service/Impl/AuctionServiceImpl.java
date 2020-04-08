package com.schoolc2c.trade.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Auction;
import com.schoolc2c.service.AuctionService;
import com.schoolc2c.trade.mapper.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionMapper auctionMapper;


    @Override
    public String releaseAuction(Auction auction) {

        int flag = auctionMapper.insert(auction);

        if (flag>0){
            return "success";
        }
        return "fail";
    }
}
