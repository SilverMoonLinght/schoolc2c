package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.bean.Auction;
import com.schoolc2c.service.AuctionService;
import com.schoolc2c.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

}
