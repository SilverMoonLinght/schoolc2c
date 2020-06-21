package com.schoolc2c.auto.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.auto.mapper.AuctionMapper;
import com.schoolc2c.auto.mapper.HotProductMapper;
import com.schoolc2c.auto.mapper.MessageMapper;
import com.schoolc2c.auto.mapper.ProductInfoMapper;
import com.schoolc2c.bean.*;
import com.schoolc2c.service.ProductListService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AutoUpdate {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    HotProductMapper hotProductMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    AuctionMapper auctionMapper;

    @Autowired
    JestClient jestClient;



    @Scheduled(fixedRate = 1000*300)
    @Async
    public void updateHotProduct(){
        System.out.println("----updateHotProduct start----");
        List<HotProduct> hotProducts = messageMapper.selectHotProductList();
        hotProductMapper.deleteAll();
        hotProductMapper.insertHotProducts(hotProducts);
        System.out.println("----updateHotProduct end----");
    }



    @Scheduled(fixedRate = 1000*180)
    @Async
    public void putProductIntoElasticSearch() throws IOException {
        System.out.println("-----putstart-----");
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList = productInfoMapper.selectAll();
        List<ProductSearchInfo> productSearchInfos = new ArrayList<>();

        for (ProductInfo productInfo : productInfoList) {
            ProductSearchInfo productSearchInfo = new ProductSearchInfo();

            BeanUtils.copyProperties(productInfo, productSearchInfo);

            productSearchInfos.add(productSearchInfo);
        }

        for (ProductSearchInfo productSearchInfo : productSearchInfos) {
            Index put = new Index.Builder(productSearchInfo).index("schoolc2c").type("ProductInfo").id(productSearchInfo.getId()).build();
            jestClient.execute(put);
        }
        System.out.println(("------putend-------"));
    }

    @Scheduled(fixedRate = 1000*120)
    @Async
    public void deleteOverdueAuction() throws ParseException {

        System.out.println("----deleteOverdueAuction start----");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        List<Auction> auctionList = auctionMapper.selectAll();

        for (Auction auction : auctionList) {
            if (auction.getEndtime().before(simpleDateFormat.parse(simpleDateFormat.format(new Date())))){
                auctionMapper.delete(auction);

            }
        }

        System.out.println("----deleteOverdueAuction end----");
    }
}
