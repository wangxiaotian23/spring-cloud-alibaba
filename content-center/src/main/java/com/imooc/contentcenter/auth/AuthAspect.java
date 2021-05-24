package com.imooc.contentcenter.auth;

import com.imooc.contentcenter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author lawhen
 * @Date 2021/1/22
 */
@Aspect
@Component
public class AuthAspect {

    @Autowired
    private JwtOperator jwtOperator;

    @Around("@annotation(CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) throws Throwable {
        //从header里面获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        //校验token是否合法
        boolean result = jwtOperator.validateToken(token);
        if (!result){
            throw new SecurityException("Token不合法！");
        }
        //校验成功就将用户的信息设置到request的attribute里面
        Claims claims = jwtOperator.getClaimsFromToken(token);
        request.setAttribute("id",claims.get("id"));
        request.setAttribute("wxNickName",claims.get("wxNickName"));
        request.setAttribute("role",claims.get("role"));
        return point.proceed();
    }

    @Around("@annotation(CheckAuth)")
    public Object checkAuth(ProceedingJoinPoint point) throws Throwable {
        //从header里面获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        //校验token是否合法
        boolean result = jwtOperator.validateToken(token);
        if (!result){
            throw new SecurityException("Token不合法！");
        }
        //校验成功就将用户的信息设置到request的attribute里面
        Claims claims = jwtOperator.getClaimsFromToken(token);
        String role = (String) claims.get("role");
        MethodSignature signature = (MethodSignature) point.getSignature();
        CheckAuth checkAuth = signature.getMethod().getAnnotation(CheckAuth.class);
        String value = checkAuth.value();
        if (!Objects.equals(role,value)){
            throw new SecurityException("用户无权访问！");
        }
        request.setAttribute("id",claims.get("id"));
        request.setAttribute("wxNickName",claims.get("wxNickName"));
        request.setAttribute("role",claims.get("role"));


        return point.proceed();
    }

}
