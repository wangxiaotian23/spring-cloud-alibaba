package com.imooc.contentcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lawhen
 * @Date 2021/1/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddBonusMsgDTO {

    private Integer userId;

    private Integer bonus;


}
