package com.imooc.contentcenter.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author lawhen
 * @Date 2021/1/22
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuth {

    String value();

}
