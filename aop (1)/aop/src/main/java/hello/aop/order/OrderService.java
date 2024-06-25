package hello.aop.order;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {



    private final OrderRepository orderRepository;

    public void orderitem(String itemid){

        log.info("서시브실행");
        orderRepository.save(itemid);
    }
}
