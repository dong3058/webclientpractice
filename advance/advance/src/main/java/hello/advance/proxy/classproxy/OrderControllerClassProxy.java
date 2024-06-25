package hello.advance.proxy.classproxy;

import hello.advance.proxy.v2_proxy.OrderControllerV2;
import hello.advance.proxy.v2_proxy.OrderServiceV2;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;

public class OrderControllerClassProxy extends OrderControllerV2 {
    private final OrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerClassProxy(OrderServiceV2 orderService, OrderControllerV2 target, LogTrace logTrace) {
        super(orderService);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status=null;
        try{
            status=logTrace.begin("orderservice.request()");
            String result=target.request(itemId);
            logTrace.end(status);
            return result;
        }
        catch(Exception e){
            logTrace.exception(status,e);
            throw e;
        }

    }
}
