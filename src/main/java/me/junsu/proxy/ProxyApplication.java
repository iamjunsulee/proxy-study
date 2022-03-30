package me.junsu.proxy;

import me.junsu.proxy.config.v1_proxy.ConcreteProxyConfig;
import me.junsu.proxy.config.v2_dynamicProxy.DynamicProxyConfig;
import me.junsu.proxy.trace.logtrace.LogTrace;
import me.junsu.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(DynamicProxyConfig.class)
@SpringBootApplication(scanBasePackages = "me.junsu.proxy.app")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
