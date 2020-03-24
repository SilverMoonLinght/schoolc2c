package com.schoolc2c.admin.mapper;

import com.schoolc2c.bean.Admin;

import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {
    Admin selectAdmin(String adminname);
}
