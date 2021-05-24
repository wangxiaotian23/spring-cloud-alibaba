package com.imooc.contentcenter.feignclient;

import com.imooc.contentcenter.dto.UserAddBonusDTO;
import com.imooc.contentcenter.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @Author lawhen
 * @Date 2021/1/4
 */
@Component
public class UserCenterFallback implements UserCenterFeignClient{


    @Override
    public UserDTO findById(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setWxNickname("默认用户");
        return userDTO;
    }

    @Override
    public UserDTO query(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO addBonus(UserAddBonusDTO userAddBonusDTO) {
        return null;
    }
}
