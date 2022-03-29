package me.junsu.proxy;

import me.junsu.proxy.config.AppConfigV1;
import me.junsu.proxy.config.AppConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppConfigV1.class, AppConfigV2.class})
@SpringBootApplication(scanBasePackages = "me.junsu.proxy.app")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
