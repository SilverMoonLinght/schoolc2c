package com.schoolc2c.trade;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductSearchInfo;

import com.schoolc2c.service.ProductListService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Schoolc2cTradeServiceApplicationTests {

    @Reference
    ProductListService productListService;

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() throws IOException {

        List<ProductSearchInfo> productSearchInfos = new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder =new MatchQueryBuilder("skuName","小米");
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);


        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("schoolc2c").addType("ProductInfo").build();

        SearchResult execute = jestClient.execute(search);
        Long total = execute.getTotal();
        List<SearchResult.Hit<ProductSearchInfo, Void>> hits = execute.getHits(ProductSearchInfo.class);
        for (SearchResult.Hit<ProductSearchInfo, Void> hit : hits) {
            ProductSearchInfo productSearchInfo = hit.source;

            productSearchInfos.add(productSearchInfo);
        }

        System.out.println(productSearchInfos.size());
    }

    @Test
    public void put() throws IOException {
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList = productListService.getAllProduct();
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
    }

}
