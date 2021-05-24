package com.imooc.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign脱离ribbon单独调用其他服务
 * @Author lawhen
 * @Date 2020/12/30
 */
@FeignClient(name = "baidu",url = "http://www.baidu.com")
public interface TestBaiduFeignClient {

    @GetMapping("")
    String index();

}
