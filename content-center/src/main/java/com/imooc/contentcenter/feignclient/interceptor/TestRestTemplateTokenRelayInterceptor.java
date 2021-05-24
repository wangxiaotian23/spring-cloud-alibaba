package com.imooc.contentcenter.feignclient.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * restTemplate实现token传递
 * @Author lawhen
 * @Date 2021/1/22
 */
public class TestRestTemplateTokenRelayInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest httpRequest = attributes.getRequest();

        String token = httpRequest.getHeader("token");
        if (StringUtils.isNotBlank(token)){
            //token传递
            HttpHeaders headers = request.getHeaders();
            headers.add("token",token);
        }
        return execution.execute(request,body);
    }
}
