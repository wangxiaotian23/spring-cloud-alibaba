package com.imooc.contentcenter.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
public interface MySource {

    String MY_OUTPUT = "my-output";

    @Output(MY_OUTPUT)
    MessageChannel output();

}
