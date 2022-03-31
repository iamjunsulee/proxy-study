package me.junsu.proxy.config.v2_dynamicProxy;

import me.junsu.proxy.app.v1.*;
import me.junsu.proxy.config.v2_dynamicProxy.handler.LogTraceBasicHandler;
import me.junsu.proxy.config.v2_dynamicProxy.handler.LogTraceFilterHandler;
import me.junsu.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {
    private final static String[] PATTERNS = {"order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderControllerImpl, logTrace, PATTERNS));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderServiceImpl, logTrace, PATTERNS));
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepositoryImpl, logTrace, PATTERNS));
    }
}
