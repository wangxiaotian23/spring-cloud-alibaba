package com.imooc.contentcenter.feignclient;

import com.imooc.contentcenter.dto.UserAddBonusDTO;
import com.imooc.contentcenter.dto.UserDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author lawhen
 * @Date 2021/1/4
 */
@Component
@Slf4j
public class UserCenterFallbackFactory implements FallbackFactory<UserCenterFeignClient> {
    @Override
    public UserCenterFeignClient create(Throwable throwable) {
        return new UserCenterFeignClient() {
            @Override
            public UserDTO findById(Integer id) {
                log.warn("远程调用被限流/降级了", throwable);
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
        };
    }
}
