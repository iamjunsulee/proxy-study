package me.junsu.proxy.cglibProxy.code;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class ConcreteServiceCallbackFilter implements CallbackFilter {
    //메소드에 따라 다른 Callback을 적용시키기 위한 필터, 반환값은 등록하는 Callback 배열의 인덱스이다.
    @Override
    public int accept(Method method) {
        String methodName = method.getName();
        if (methodName.equals("call")) {
            return 0;
        } else {
            return 1;
        }
    }
}
