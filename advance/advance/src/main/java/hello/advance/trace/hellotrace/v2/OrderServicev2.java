 package hello.advance.trace.hellotrace.v2;


import hello.advance.trace.TraceId;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServicev2 {


    private final OrderRepositoryv2 orderRepositoryv2;
    private final HelloTraceV2 helloTracev2;
    public void orderitem(TraceId traceId,String itemid){

        TraceStatus status= helloTracev2.beginsync(traceId,"Orderservice.orderitem()");
        try {
            orderRepositoryv2.save(status.getTraceId(),itemid);
            helloTracev2.end(status);
        }
        catch(Exception e){
            helloTracev2.exception(status,e);
            throw e;
        }

    }


}
