package com.imooc.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 用于支持restful的sentinel  /1 /2 /3
 * @Author lawhen
 * @Date 2021/1/4
 */
@Component
public class MyUrlCleaner implements UrlCleaner {

    @Override
    public String clean(String s) {
        String[] split = s.split("/");
        return Arrays.stream(split).map(s1 -> {
            if (NumberUtils.isNumber(s1)){
                return "{number}";
            }
            return s1;
        }).reduce((a,b)->a+"/"+b).orElse("");
    }
}
