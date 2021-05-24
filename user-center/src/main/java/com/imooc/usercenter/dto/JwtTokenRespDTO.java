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
public class JwtTokenRespDTO {

    private String token;

    private Long expirationTime;
}
