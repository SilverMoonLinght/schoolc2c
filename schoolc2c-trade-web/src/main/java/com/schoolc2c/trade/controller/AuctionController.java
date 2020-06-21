package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.Auction;
import com.schoolc2c.bean.AuctionRecord;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.service.AuctionService;
import com.schoolc2c.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AuctionController {

    @Reference
    AuctionService auctionService;

    @RequestMapping("releaseAuction")
    @ResponseBody
    @LoginRequired
    public String releaseAuction(HttpServletRequest request, @RequestBody Auction auction){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();
        auction.setUserId(id);

        String status = auctionService.releaseAuction(auction);

        return status;
    }

    @RequestMapping("getAuctionList")
    @ResponseBody
    @PassToken
    public Pages getAuctionList(String catalog3Id,int pageNum,int pageSize){

        return auctionService.getAuctionList(catalog3Id,pageNum,pageSize);
    }

    @RequestMapping("getAuctionInfo")
    @ResponseBody
    @PassToken
    public Auction getAuctionInfo(String id){
        return auctionService.getAuctionInfo(id);
    }

    @RequestMapping("submitAuctionPrice")
    @ResponseBody
    @LoginRequired
    public String submitAuctionPrice(HttpServletRequest request,@RequestBody AuctionRecord auctionRecord){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();
        auctionRecord.setUid(id);

        return auctionService.submitAuctionPrice(auctionRecord);
    }

    @RequestMapping("getAuctionRecord")
    @ResponseBody
    @PassToken
    public List<AuctionRecord> getAuctionRecord(String aid){
        return auctionService.getAuctionRecord(aid);
    }

    @RequestMapping("getMaxPrice")
    @ResponseBody
    @PassToken
    public String getMaxPrice(String aid){
        return auctionService.getMaxPrice(aid);
    }

    @RequestMapping("getAuctionByUser")
    @ResponseBody
    @LoginRequired
    public List<Auction> getAuctionByUser(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();

        return auctionService.getAuctionByUser(id);
    }

    @RequestMapping("editAuction")
    @ResponseBody
    @LoginRequired
    public String editAuction(@RequestBody Auction auction){
        return auctionService.editAuction(auction);
    }

    @RequestMapping("removeAuction")
    @ResponseBody
    @LoginRequired
    public  String removeAuction(@RequestBody Auction auction){
        return auctionService.removeAuction(auction);
    }

    @RequestMapping("getAuctionRecordByUser")
    @ResponseBody
    @LoginRequired
    public List<Auction> getAuctionRecordByUser(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();

        return auctionService.getAuctionRecordByUser(id);
    }
}
