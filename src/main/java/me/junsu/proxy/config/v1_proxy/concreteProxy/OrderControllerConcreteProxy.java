package me.junsu.proxy.config.v1_proxy.concreteProxy;

import me.junsu.proxy.app.v2.OrderControllerV2;
import me.junsu.proxy.trace.TraceStatus;
import me.junsu.proxy.trace.logtrace.LogTrace;

//상속을 사용하기 때문에 여전히 상속에 대한 제약이 있다.
//final 메소드는 오버라이딩 할 수 없다.
//부모클래스의 생성자를 호출해야한다.(문법상)
public class OrderControllerConcreteProxy extends OrderControllerV2 {
    private final OrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace logTrace) {
        super(null);    //프록시로 사용할 목적이기 때문에 부모 생성자 사용 x
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String orderRequest(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.orderRequest()");
            String result = target.orderRequest(itemId);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLogRequest() {
        return target.noLogRequest();
    }
}
