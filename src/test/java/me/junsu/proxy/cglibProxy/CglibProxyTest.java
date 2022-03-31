package me.junsu.proxy.cglibProxy;

import me.junsu.proxy.cglibProxy.code.CallMethodInterceptor;
import me.junsu.proxy.cglibProxy.code.ConcreteService;
import me.junsu.proxy.cglibProxy.code.ConcreteServiceCallbackFilter;
import me.junsu.proxy.cglibProxy.code.TimeInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest {
    @Test
    public void cglibTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);  //원본 객체
        enhancer.setCallback(new TimeInterceptor());    //MethodInterceptor 가 Callback 을 상속받으므로 setCallback 메소드를 통해 등록
        ConcreteService proxy = (ConcreteService) enhancer.create();
        proxy.operation();
    }

    @Test
    public void cglibFilterTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        //CallbackFilter의 반환값에 따라 해당 인덱스의 Callback 실행 
        enhancer.setCallbacks(new Callback[]{new CallMethodInterceptor(), new TimeInterceptor()});
        enhancer.setCallbackFilter(new ConcreteServiceCallbackFilter());
        ConcreteService proxy = (ConcreteService) enhancer.create();
        proxy.call();
        proxy.operation();
    }
}
