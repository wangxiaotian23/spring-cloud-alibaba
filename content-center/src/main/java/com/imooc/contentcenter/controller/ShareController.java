package com.imooc.contentcenter.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.contentcenter.auth.CheckLogin;
import com.imooc.contentcenter.domain.Share;
import com.imooc.contentcenter.dto.ShareDTO;
import com.imooc.contentcenter.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lawhen
 * @Date 2020/12/28
 */
@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @GetMapping("/{id}")
    @CheckLogin
    public ShareDTO findById(@PathVariable Integer id){
        return shareService.findById(id);
    }

    @GetMapping("/q")
    public PageInfo<Share> q(@RequestParam(required = false) String title,
                             @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        if (pageSize>100){
            pageSize = 100;
        }
        return this.shareService.q(title,pageNo,pageSize);
    }

    @GetMapping("/exchange/{id}")
    @CheckLogin
    public Share exchangeById(@PathVariable Integer id, HttpServletRequest request){
        return this.shareService.exchangeById(id,request);
    }

}
