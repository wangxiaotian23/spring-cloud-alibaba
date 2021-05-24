package com.imooc.usercenter.dao;

import com.imooc.usercenter.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}