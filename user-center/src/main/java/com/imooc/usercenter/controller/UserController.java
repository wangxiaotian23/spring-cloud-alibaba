package com.imooc.usercenter.controller;

import com.imooc.usercenter.auth.CheckLogin;
import com.imooc.usercenter.domain.User;
import com.imooc.usercenter.dto.JwtTokenRespDTO;
import com.imooc.usercenter.dto.LoginRespDTO;
import com.imooc.usercenter.dto.UserLoginDTO;
import com.imooc.usercenter.dto.UserRespDTO;
import com.imooc.usercenter.service.UserService;
import com.imooc.usercenter.util.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lawhen
 * @Date 2020/12/28
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtOperator jwtOperator;

    @GetMapping("/{id}")
    @CheckLogin
    public User findById(@PathVariable Integer id) {
        log.info("命中");
        return userService.findById(id);
    }

    @GetMapping("/q")
    public User query(User user) {
        return user;
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("exNickName", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        long expireTime = jwtOperator.getExpirationDateFromToken(token).getTime();
        log.info("用户{}登录成功，生成的token={}，有效期到:{}", loginDTO.getWxNickName(), token, expireTime);
        return LoginRespDTO.builder().user(
                UserRespDTO.builder()
                        .id(user.getId())
                        .wxNickName(user.getWxNickname())
                        .avatarUrl(user.getAvatarUrl())
                        .bonus(user.getBonus())
                        .build()).token(
                JwtTokenRespDTO.builder()
                        .token(token)
                        .expirationTime(expireTime)
                        .build()
        ).build();
    }

}
