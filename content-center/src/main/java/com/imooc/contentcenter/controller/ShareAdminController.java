package com.imooc.contentcenter.controller;

import com.imooc.contentcenter.auth.CheckAuth;
import com.imooc.contentcenter.domain.Share;
import com.imooc.contentcenter.dto.AuditDTO;
import com.imooc.contentcenter.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author lawhen
 * @Date 2021/1/4
 */
@RestController
@RequestMapping("/admin/shares")
public class ShareAdminController {

    @Autowired
    private ShareService shareService;

    @PutMapping("/audit/{id}")
    @CheckAuth("admin")
    public Share auditById(@PathVariable Integer id, @RequestBody  AuditDTO auditDTO){
        //认证授权
        return shareService.auditByIdStream(id,auditDTO);
    }

}
