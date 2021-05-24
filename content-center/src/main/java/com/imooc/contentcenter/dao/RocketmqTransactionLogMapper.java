package com.imooc.contentcenter.dao;

import com.imooc.contentcenter.domain.RocketmqTransactionLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Repository
public interface RocketmqTransactionLogMapper extends Mapper<RocketmqTransactionLog> {
}
