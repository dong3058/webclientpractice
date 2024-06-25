package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV4.class)
//@Import({AspectV5.logaspect.class,AspectV5.Transcation.class})
@Import(AspectV6.class)
public class aoptest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;


    @Test
    void apoinfo(){
        log.info("is aopproxy orderservice={}", AopUtils.isAopProxy(orderService));
        log.info("is aopproxy orderrepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void    sucess(){
        orderService.orderitem("itema");
    }
    @Test
    void fail(){

        Assertions.assertThatThrownBy(()->orderService.orderitem("ex")).isInstanceOf(IllegalStateException.class);
    }


}
