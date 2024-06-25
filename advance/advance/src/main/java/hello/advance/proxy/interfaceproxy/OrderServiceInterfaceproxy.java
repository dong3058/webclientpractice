package hello.advance.proxy.interfaceproxy;

import hello.advance.proxy.v1_proxy.OrderRepoV1;
import hello.advance.proxy.v1_proxy.OrderServiceV1;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
@RequiredArgsConstructor
public class OrderServiceInterfaceproxy implements OrderServiceV1 {

    private final OrderServiceV1 target;
    private final LogTrace logTrace;


    @Override
    public void orderItem(String itemId) {
        TraceStatus status=null;
        try{
            status=logTrace.begin("orderservice.request()");
            target.orderItem(itemId);
            logTrace.end(status);
        }
        catch(Exception e){
            logTrace.exception(status,e);
            throw e;
        }

    }


    public void sleep(int millis){
        try{
            Thread.sleep(millis);}
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
