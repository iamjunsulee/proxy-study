package me.junsu.proxy.config.v2_dynamicProxy.handler;

import me.junsu.proxy.trace.TraceStatus;
import me.junsu.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//특정 메소드만 로그를 출력하기 위해서 필터 추가
public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target;    //프록시를 통해 호출할 실제 객체
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        String methodName = method.getName();

        //메소드 명으로 필터링
        //패턴에 맞는 메소드가 아닌 경우, 로그 출력 안함.
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + methodName + "()";
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
