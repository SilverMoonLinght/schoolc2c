package com.schoolc2c.bean;

import java.io.Serializable;
import java.util.List;

public class Pages implements Serializable {
    private int totalPageNum;
    private int pageNum;
    private List<User> userList;
    private List<ProductInfo> productInfoList;
    private List<ProductWanted> productWanteds;
    private List<Auction> auctions;
    private List<ProductSearchInfo> productSearchInfos;

    public List<ProductSearchInfo> getProductSearchInfos() {
        return productSearchInfos;
    }

    public void setProductSearchInfos(List<ProductSearchInfo> productSearchInfos) {
        this.productSearchInfos = productSearchInfos;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public List<ProductWanted> getProductWanteds() {
        return productWanteds;
    }

    public void setProductWanteds(List<ProductWanted> productWanteds) {
        this.productWanteds = productWanteds;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


}
