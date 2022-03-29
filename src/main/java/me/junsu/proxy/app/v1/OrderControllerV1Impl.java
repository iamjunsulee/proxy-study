package me.junsu.proxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1 {
    private final OrderServiceV1 orderService;

    public OrderControllerV1Impl(OrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String orderRequest(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLogRequest() {
        return "no-log";
    }
}
