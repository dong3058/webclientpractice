package hello.advance.trace.hellotrace.v0;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerv0 {

    private final OrderService orderService;


    @GetMapping("/v0/request")
    public String request(String itemid){
        orderService.orderitem(itemid);

        return "ok";
    }
}
