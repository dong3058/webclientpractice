package hello.advance.trace.hellotrace.v1;


import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServicev1 {


    private final OrderRepositoryv1 orderRepositoryv1;
    private final HelloTraceV1 helloTracev1;
    public void orderitem(String itemid){

        TraceStatus status= helloTracev1.begin("Orderservice.orderitem()");
        try {
            orderRepositoryv1.save(itemid);
            helloTracev1.end(status);
        }
        catch(Exception e){
            helloTracev1.exception(status,e);
            throw e;
        }

    }


}
