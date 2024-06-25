package hello.advance.proxy.v1_proxy;

import hello.advance.proxy.v1_proxy.OrderServiceV1;

public class OrderServiceV1Impl implements OrderServiceV1 {


    private final OrderRepoV1 orderRepo;

    public OrderServiceV1Impl(OrderRepoV1 orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepo.save(itemId);

    }
}
