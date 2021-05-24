package com.imooc.usercenter.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
public interface MySink {

    String MY_INPUT = "my-input";

    @Input(MY_INPUT)
    SubscribableChannel input();
}
