package hello.advance.trace.hellotrace.v3;


import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV2;
import hello.advance.trace.hellotrace.v2.OrderServicev2;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerv3 {

    private final OrderServicev3 orderService;
    private final LogTrace trace;


    @GetMapping("/v2/request")
    public String request(String itemid){

        TraceStatus status= trace.begin("Ordercontroller.request()");
        try {
            orderService.orderitem(itemid);
            trace.end(status);
        }
        catch(Exception e){
            trace.exception(status,e);
            throw e;
        }
        return "ok";
    }
}
