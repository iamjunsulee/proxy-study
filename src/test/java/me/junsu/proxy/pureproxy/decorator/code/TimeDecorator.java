package me.junsu.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator {
    public TimeDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("Time Decorator 실행");
        long startTime = System.currentTimeMillis();
        String result = super.operation();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Time Decorator 종료 resultTime={}ms", resultTime);
        return result;
    }
}
