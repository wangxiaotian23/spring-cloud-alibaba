package com.imooc.usercenter.dto;

import lombok.*;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserAddBonusMsgDTO {

    private Integer userId;

    private Integer bonus;


}
