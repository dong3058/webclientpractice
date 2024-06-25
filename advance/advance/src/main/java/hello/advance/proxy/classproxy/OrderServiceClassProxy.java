package hello.advance.proxy.classproxy;

import hello.advance.proxy.v2_proxy.OrderRepoV2;
import hello.advance.proxy.v2_proxy.OrderServiceV2;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;


public class OrderServiceClassProxy extends OrderServiceV2 {


    private final OrderServiceV2 orderServiceV2;
    private final LogTrace logTrace;

    public OrderServiceClassProxy(OrderRepoV2 orderRepo, OrderServiceV2 orderServiceV2, LogTrace logTrace) {
        super(null);
        this.orderServiceV2 = orderServiceV2;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {

    }
}
