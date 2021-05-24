package com.imooc.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义谓词工厂，以RoutePredicateFactory结尾
 * @Author lawhen
 * @Date 2021/1/22
 */
@Component
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {

    public TimeBetweenRoutePredicateFactory(){
        super(TimeBetweenConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                LocalTime now = LocalTime.now();
                return now.isAfter(start) && now.isBefore(end);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("start","end");
    }
}
