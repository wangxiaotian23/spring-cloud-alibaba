package com.imooc.contentcenter.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfig.RibbonConfig;

/**
 *
 * @Author lawhen
 * @Date 2020/12/29
 */
//@Configuration
//用户中心负载均衡策略
//@RibbonClient(name = "user-center",configuration = RibbonConfig.class)
//全局配置
//@RibbonClients(defaultConfiguration = RibbonConfig.class)
public class UserCenterRibbonConfig {
}
