package com.schoolc2c.service;

import com.schoolc2c.bean.Booth;
import com.schoolc2c.bean.BoothProduct;

import java.util.List;

public interface BoothService {
    Booth getBoothInfo(String uid);

    String createBooth(String uid);

    List<BoothProduct> getBoothProductList(String bid);
}
