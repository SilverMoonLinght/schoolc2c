package com.schoolc2c.admin.mapper;

import com.schoolc2c.bean.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<User> selectAllUser();

    User selectUser(String username);

    List<User> selectPageUser(@Param("startNum")int startNum, @Param("pageSize")int pageSize);
}
