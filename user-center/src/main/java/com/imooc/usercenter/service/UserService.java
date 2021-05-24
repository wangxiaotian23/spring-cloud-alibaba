package com.imooc.usercenter.service;

import com.imooc.usercenter.dao.BonusEventLogMapper;
import com.imooc.usercenter.dao.UserMapper;
import com.imooc.usercenter.domain.BonusEventLog;
import com.imooc.usercenter.domain.User;
import com.imooc.usercenter.dto.UserAddBonusMsgDTO;
import com.imooc.usercenter.dto.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author lawhen
 * @Date 2020/12/28
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;


    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {

        //加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
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

    public User login(UserLoginDTO loginDTO) {
        User user = userMapper.selectByPrimaryKey(loginDTO.getCode());
        if (user == null) {
            User userToSave = User.builder()
                    .wxId("asc")
                    .bonus(300)
                    .wxNickname(loginDTO.getWxNickName())
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            userMapper.insertSelective(userToSave);
            return userToSave;
        }else {
            return user;
        }
    }
}
