package hello.advance.proxy.classproxy;

import hello.advance.LogTraceConfig;
import hello.advance.proxy.v2_proxy.OrderRepoV2;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
@RequiredArgsConstructor
public class OrderRepoClassProxy extends OrderRepoV2 {


    private final OrderRepoV2 orderrepo;
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
}
