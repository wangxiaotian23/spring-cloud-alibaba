package com.imooc.usercenter.rocketmq;

import com.imooc.usercenter.dao.BonusEventLogMapper;
import com.imooc.usercenter.dao.UserMapper;
import com.imooc.usercenter.domain.BonusEventLog;
import com.imooc.usercenter.domain.User;
import com.imooc.usercenter.dto.UserAddBonusMsgDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Slf4j
@Service
//@RocketMQMessageListener(consumerGroup = "consumer-group",topic = "add-bonus")
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        //加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+userAddBonusMsgDTO.getBonus());
        userMapper.updateByPrimaryKeySelective(user);
        //记录日志
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                .userId(userId)
                .value(userAddBonusMsgDTO.getBonus())
                .event("CONTRIBUTE")
                .description("投稿加积分")
                .build());
        log.info("积分添加完毕");
    }

}
