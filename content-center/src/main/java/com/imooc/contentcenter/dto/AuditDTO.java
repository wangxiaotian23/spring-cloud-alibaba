package com.imooc.contentcenter.dto;

import lombok.Data;

/**
 * @Author lawhen
 * @Date 2021/1/4
 */
@Data
public class AuditDTO {


    private AuditStatusEnum auditStatusEnum;

    private String reason;

}
