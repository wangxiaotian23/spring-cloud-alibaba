package com.imooc.contentcenter.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lawhen
 * @Date 2020/12/30
 */
//不写下面这个注解，否则会出现父子上下文重复扫描
//还可以挪到ComponentScan扫描之外
//@Configuration
public class UserCenterFeignConfig {

    /**
     * 让feign打印所有的日志信息
     */
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }

}
