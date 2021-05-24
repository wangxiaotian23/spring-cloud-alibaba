package com.imooc.contentcenter.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;

/**
 * 自定义负载均衡策略，支持nacos权重
 *
 * @Author lawhen
 * @Date 2020/12/29
 */
@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //读取配置文件，并初始化
    }

    @Override
    public Server choose(Object o) {
        try {
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            //想要请求的微服务的名称
            String name = loadBalancer.getName();
            //拿到服务发现的相关API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            //nacos client自动通过基于权重的负载均衡算法，给我们选择一个实例
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("选择的实例是:port={},instance={}",instance.getPort(),instance.getIp());
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
//    @Slf4j
//public class NacosWeightedRule extends AbstractLoadBalancerRule{
//    @Override
//    public void initWithNiwsConfig(IClientConfig clientConfig) {
//        //读取配置文件，并初始化
//    }
//
//    @Override
//    public Server choose(Object key) {
//        ILoadBalancer loadBalancer = this.getLoadBalancer();
//        log.info();
//    }
//}
