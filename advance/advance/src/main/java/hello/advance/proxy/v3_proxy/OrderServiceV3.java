package hello.advance.proxy.v3_proxy;

import hello.advance.proxy.v2_proxy.OrderRepoV2;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {
    private final OrderRepoV3 orderRepo;

    public OrderServiceV3(OrderRepoV3 orderRepo) {
        this.orderRepo = orderRepo;
    }
    public void orderItem(String itemId) {
        orderRepo.save(itemId);

    }
}
