package com.imooc.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于授权规则
 * @Author lawhen
 * @Date 2021/1/4
 */
//@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        //从请求参数中获取名为origin的参数并返回
        //如果获取不到origin参数，那么久抛异常
        String origin = httpServletRequest.getParameter("origin");
        if (StringUtils.isBlank(origin)){
            throw new IllegalArgumentException("origin must be no blank");
        }
        return origin;
    }
}
