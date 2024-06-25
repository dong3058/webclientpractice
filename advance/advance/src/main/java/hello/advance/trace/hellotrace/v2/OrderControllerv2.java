package hello.advance.trace.hellotrace.v2;


import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerv2 {

    private final OrderServicev2 orderServicev2;
    private final HelloTraceV2 helloTracev2;


    @GetMapping("/v2/request")
    public String request(String itemid){

        TraceStatus status= helloTracev2.begin("Ordercontroller.request()");
        try {
            orderServicev2.orderitem(status.getTraceId(),itemid);
            helloTracev2.end(status);
        }
        catch(Exception e){
            helloTracev2.exception(status,e);
            throw e;
        }
        return "ok";
    }
}
