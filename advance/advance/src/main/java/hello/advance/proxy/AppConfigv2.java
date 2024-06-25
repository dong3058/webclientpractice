package hello.advance.proxy;

import hello.advance.proxy.v1_proxy.*;
import hello.advance.proxy.v2_proxy.OrderControllerV2;
import hello.advance.proxy.v2_proxy.OrderRepoV2;
import hello.advance.proxy.v2_proxy.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigv2 {

    @Bean
    public OrderControllerV2 orderControllerV2(){
        return new OrderControllerV2(orderServiceV2());
    }
    @Bean
    public OrderServiceV2 orderServiceV2(){
        return new OrderServiceV2(orderRepoV2());
    }
    @Bean
    public OrderRepoV2 orderRepoV2(){
        return new OrderRepoV2();
    }
}
