package me.junsu.proxy.cglibProxy.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //obj : 원본 객체
        //method : 원본 객체의 호출될 메소드
        //args : 원본 객체에 전달될 파라미터
        //methodProxy : CGLIB가 제공하는 원본 객체의 프록시 객체
        log.info("TimeInterceptor 실행");
        long startTime = System.currentTimeMillis();
        Object result = methodProxy.invokeSuper(obj, args);
        long endTime = System.currentTimeMillis();
        log.info("TimeInterceptor 종료, resultTime = {}", endTime - startTime);
        return result;
    }
}
