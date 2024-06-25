package hello.advance.proxy;

import hello.advance.proxy.v1_proxy.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(){
        return new OrderControllerV1Impl(orderServiceV1());
    }
    @Bean
    public OrderServiceV1 orderServiceV1(){
        return new OrderServiceV1Impl(orderRepoV1());
    }
    @Bean
    public OrderRepoV1 orderRepoV1(){
        return new OrderRepoV1Impl();
    }
}
