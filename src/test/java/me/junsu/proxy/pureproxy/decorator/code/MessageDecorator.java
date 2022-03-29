package me.junsu.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator {
    public MessageDecorator(Component component) {
        super(component);
    }
    @Override
    public String operation() {
        log.info("Message Decorator 실행");
        String result = super.operation();
        String newResult = "******" + result + "******";
        log.info("Message Decorator 종료 적용전 결과={}, 적용후 결과={}", result, newResult);
        return newResult;
    }
}
