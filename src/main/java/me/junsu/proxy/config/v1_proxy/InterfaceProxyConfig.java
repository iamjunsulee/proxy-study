package me.junsu.proxy.config.v1_proxy;

import me.junsu.proxy.app.v1.*;
import me.junsu.proxy.config.v1_proxy.interfaceProxy.OrderControllerInterfaceProxy;
import me.junsu.proxy.config.v1_proxy.interfaceProxy.OrderRepositoryInterfaceProxy;
import me.junsu.proxy.config.v1_proxy.interfaceProxy.OrderServiceInterfaceProxy;
import me.junsu.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    //프록시 객체를 스프링 빈으로 등록함.
    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(orderControllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, logTrace);
    }
}
