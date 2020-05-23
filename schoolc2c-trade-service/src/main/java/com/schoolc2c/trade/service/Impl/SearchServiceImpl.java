package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductSearchInfo;
import com.schoolc2c.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    JestClient jestClient;

    @Override
    public Pages searchProduct(int pageNum, int pageSize, String searchInfo) throws IOException {
        Pages pages=  new Pages();
        int startNum = (pageNum-1)*pageSize;

        List<ProductSearchInfo> productSearchInfos = new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder =new MatchQueryBuilder("skuName",searchInfo);
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.from(startNum);
        searchSourceBuilder.size(pageSize);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("schoolc2c").addType("ProductInfo").build();

        SearchResult execute = jestClient.execute(search);
        Long total = execute.getTotal();
        List<SearchResult.Hit<ProductSearchInfo, Void>> hits = execute.getHits(ProductSearchInfo.class);
        for (SearchResult.Hit<ProductSearchInfo, Void> hit : hits) {
            ProductSearchInfo productSearchInfo = hit.source;

            productSearchInfos.add(productSearchInfo);
        }

        pages.setTotalPageNum(total.intValue());
        pages.setProductSearchInfos(productSearchInfos);

        return pages;
    }
}
