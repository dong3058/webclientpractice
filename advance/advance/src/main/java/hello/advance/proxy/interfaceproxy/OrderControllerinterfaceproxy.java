package hello.advance.proxy.interfaceproxy;

import hello.advance.proxy.v1_proxy.OrderControllerV1;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerinterfaceproxy implements  OrderControllerV1 {
    private final OrderControllerV1 target;
    private final LogTrace logTrace;

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

    @Override
    public String noLog() {
        return null;
    }
}
