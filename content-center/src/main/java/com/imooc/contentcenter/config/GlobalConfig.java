package com.imooc.contentcenter.config;

import com.imooc.contentcenter.feignclient.interceptor.TestRestTemplateTokenRelayInterceptor;
import org.springframework.cloud.alibaba.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @Author lawhen
 * @Date 2020/12/29
 */
@Configuration
public class GlobalConfig {

    @Bean
    @LoadBalanced
    //@SentinelRestTemplate(blockHandler = "block",blockHandlerClass = TestControllerBlockHandlerClass.class)
    @SentinelRestTemplate
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(
                Collections.singletonList(new TestRestTemplateTokenRelayInterceptor())
        );
        return restTemplate;
    }

}
