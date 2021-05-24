package com.imooc.contentcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author lawhen
 * @Date 2021/1/4
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {

    /**
     * 未审核
     */
    NOT_TET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT

}
