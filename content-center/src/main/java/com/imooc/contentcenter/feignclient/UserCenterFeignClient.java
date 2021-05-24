package com.imooc.contentcenter.feignclient;

import com.imooc.contentcenter.dto.UserAddBonusDTO;
import com.imooc.contentcenter.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author lawhen
 * @Date 2020/12/29
 */
//代码配置日志
//@FeignClient(name = "user-center",configuration = UserCenterFeignConfig.class)
//fallback和fallbackFactory不可同时使用
//@FeignClient(name = "user-center",fallback = UserCenterFallback.class)
@FeignClient(name = "user-center",fallbackFactory = UserCenterFallbackFactory.class)
public interface UserCenterFeignClient {

    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);

    //GET多参数请求
    @GetMapping("/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);

    @PutMapping("/users/add-bonus")
    UserDTO addBonus(@RequestBody UserAddBonusDTO userAddBonusDTO);

}
