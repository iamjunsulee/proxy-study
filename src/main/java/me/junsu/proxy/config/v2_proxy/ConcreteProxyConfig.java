package me.junsu.proxy.config.v2_proxy;

import me.junsu.proxy.app.v2.OrderControllerV2;
import me.junsu.proxy.app.v2.OrderRepositoryV2;
import me.junsu.proxy.app.v2.OrderServiceV2;
import me.junsu.proxy.config.v2_proxy.concreteProxy.OrderControllerConcreteProxy;
import me.junsu.proxy.config.v2_proxy.concreteProxy.OrderRepositoryConcreteProxy;
import me.junsu.proxy.config.v2_proxy.concreteProxy.OrderServiceConcreteProxy;
import me.junsu.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        OrderControllerV2 orderControllerImpl = new OrderControllerV2(orderService(logTrace));
        return new OrderControllerConcreteProxy(orderControllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        OrderServiceV2 orderServiceImpl = new OrderServiceV2(orderRepository(logTrace));
        return new OrderServiceConcreteProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        OrderRepositoryV2 orderRepositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepositoryImpl, logTrace);
    }
}
