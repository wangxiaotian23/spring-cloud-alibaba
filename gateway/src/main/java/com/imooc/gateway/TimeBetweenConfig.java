package com.imooc.gateway;

import lombok.Data;

import java.time.LocalTime;

/**
 * @Author lawhen
 * @Date 2021/1/22
 */

@Data
public class TimeBetweenConfig {

    private LocalTime start;

    private LocalTime end;

}
