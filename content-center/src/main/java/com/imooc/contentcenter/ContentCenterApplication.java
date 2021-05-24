package com.imooc.contentcenter;

import com.imooc.contentcenter.rocketmq.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.imooc.contentcenter.dao")
@SpringBootApplication
//开启Feign并全局配置Feign打印日志
//@EnableFeignClients(defaultConfiguration = UserCenterFeignClient.class)
@EnableFeignClients

@EnableBinding({Source.class, MySource.class})
public class ContentCenterApplication {


    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

}
