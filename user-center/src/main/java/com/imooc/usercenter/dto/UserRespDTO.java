package com.imooc.usercenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lawhen
 * @Date 2021/1/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRespDTO {

    private Integer id;
    //头像地址
    private String avatarUrl;
    //积分
    private Integer bonus;

    private String wxNickName;

}
