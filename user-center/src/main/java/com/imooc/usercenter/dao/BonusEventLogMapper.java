package com.imooc.usercenter.dao;

import com.imooc.usercenter.domain.BonusEventLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BonusEventLogMapper extends Mapper<BonusEventLog> {
}