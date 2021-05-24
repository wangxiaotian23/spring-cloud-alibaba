package com.imooc.contentcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lawhen
 * @Date 2021/1/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusDTO {

    private Integer userId;

    private Integer bonus;



}
