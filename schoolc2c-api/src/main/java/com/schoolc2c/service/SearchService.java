package com.schoolc2c.service;

import com.schoolc2c.bean.Pages;

import java.io.IOException;

public interface SearchService {
    Pages searchProduct(int pageNum, int pageSize, String searchInfo) throws IOException;
}
