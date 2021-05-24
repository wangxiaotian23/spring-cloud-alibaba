package com.imooc.usercenter.controller;

import com.imooc.usercenter.domain.User;
import com.imooc.usercenter.dto.UserAddBonusDTO;
import com.imooc.usercenter.dto.UserAddBonusMsgDTO;
import com.imooc.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lawhen
 * @Date 2021/1/25
 */
@RestController
@RequestMapping("/users")
public class BonusController {

    @Autowired
    private UserService userService;

    @PutMapping("/add-bonus")
    public User addBonus(@RequestBody UserAddBonusDTO userAddBonusDTO){
        userService.addBonus(UserAddBonusMsgDTO.builder()
                .userId(userAddBonusDTO.getUserId())
                .bonus(userAddBonusDTO.getBonus())
                .build());
        return this.userService.findById(userAddBonusDTO.getUserId());
    }

}
