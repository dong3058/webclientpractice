package hello.advance.trace.hellotrace.v1;


import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerv1 {

    private final OrderServicev1 orderServicev1;
    private final HelloTraceV1 helloTracev1;


    @GetMapping("/v1/request")
    public String request(String itemid){

        TraceStatus status= helloTracev1.begin("Ordercontroller.request()");
        try {
            orderServicev1.orderitem(itemid);
            helloTracev1.end(status);
        }
        catch(Exception e){
            helloTracev1.exception(status,e);
            throw e;
        }
        return "ok";
    }
}
