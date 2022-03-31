package me.junsu.proxy.cglibProxy.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class CallMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("CallMethodInterceptor 실행");
        Object result = methodProxy.invokeSuper(obj, args);
        log.info("CallMethodInterceptor 종료");
        return result;
    }
}
