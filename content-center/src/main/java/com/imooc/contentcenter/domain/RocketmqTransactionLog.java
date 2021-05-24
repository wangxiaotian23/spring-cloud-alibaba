package com.imooc.contentcenter.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Data
@Builder
@Table(name = "rocketmq_transaction_log")
public class RocketmqTransactionLog {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "transaction_id")
    private String transactionId;

    private String log;
}
