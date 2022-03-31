package me.junsu.proxy.cglibProxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {
    public void operation() {
        log.info("비지니스 로직1 실행");
    }

    public void call() {
        log.info("비지니스 로직2 실행");
    }
}
