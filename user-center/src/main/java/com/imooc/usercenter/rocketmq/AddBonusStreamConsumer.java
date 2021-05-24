package com.imooc.usercenter.rocketmq;

import com.imooc.usercenter.dto.UserAddBonusMsgDTO;
import com.imooc.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Slf4j
@Service
public class AddBonusStreamConsumer {

    @Autowired
    private UserService userService;

    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDTO userAddBonusMsgDTO){
        log.info("通过stream收到了消息：messageBody={}",userAddBonusMsgDTO);
        userService.addBonus(userAddBonusMsgDTO);
    }

}
