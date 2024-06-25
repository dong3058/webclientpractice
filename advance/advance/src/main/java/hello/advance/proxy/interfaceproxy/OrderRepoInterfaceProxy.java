package hello.advance.proxy.interfaceproxy;

import hello.advance.proxy.v1_proxy.OrderRepoV1;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepoInterfaceProxy implements OrderRepoV1 {

    private final OrderRepoV1 orderRepoV1;
    private final LogTrace logTrace;

    @Override
    public void save(String itemId) {
        TraceStatus status=null;
        try{
            status=logTrace.begin("orderrepo.request()");
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
