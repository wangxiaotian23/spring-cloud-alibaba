package com.imooc.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lawhen
 * @Date 2021/1/4
 */
@Slf4j
public class TestControllerBlockHandlerClass {

    public static String block(String a, BlockException e) {
        log.warn("限流，或者降级了 block");
        return "限流，或者降级了 block";
    }
}
