package me.junsu.proxy.config.v1_proxy.interfaceProxy;

import lombok.RequiredArgsConstructor;
import me.junsu.proxy.app.v1.OrderControllerV1;
import me.junsu.proxy.trace.TraceStatus;
import me.junsu.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {
    private final OrderControllerV1 target; //실제 객체
    private final LogTrace logTrace;

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
