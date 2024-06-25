package hello.advance.proxy.v2_proxy;

import hello.advance.proxy.v1_proxy.OrderRepoV1;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepoV2 orderRepo;

    /*public OrderServiceV2(OrderRepoV2 orderRepo) {
        this.orderRepo = orderRepo;
    }*/
    public void orderItem(String itemId) {
        orderRepo.save(itemId);

    }
}
