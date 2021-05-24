package com.imooc.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Slf4j
@Service
public class MyTestStreamConsumer {

    @StreamListener(MySink.MY_INPUT)
    public void receive(String messageBody){
        log.info("自定义接口消费：通过stream收到了消息：messageBody={}",messageBody);
        //throw new IllegalArgumentException("模拟抛异常");
    }

    @StreamListener("errorChannel")
    public void error(Message<?> message){
        ErrorMessage errorMessage = (ErrorMessage) message;
        log.error("捕获到异常:{}",errorMessage);
    }

}
