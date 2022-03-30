package me.junsu.proxy.config.v2_dynamicProxy.handler;

import me.junsu.proxy.trace.TraceStatus;
import me.junsu.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//공통으로 사용할 로직을 담당하는 handler
//newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 메소드를 통해서 동적 프록시를 생성
//위 메소드를 보면 프록시가 구현해야할 인터페이스의 Class 배열을 인수로 넘기므로 인터페이스 형태의 V1에만 적용이 가능하다.
//이 방법의 장점은 따로 프록시 클래스들을 작성할 필요가 없다는 점이지만, 로그를 출력할 필요없는 메소드의 경우에도 로그가 출력되는 문제가 있다.
public class LogTraceBasicHandler implements InvocationHandler {
    private final Object target;    //프록시를 통해 호출할 실제 객체
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
