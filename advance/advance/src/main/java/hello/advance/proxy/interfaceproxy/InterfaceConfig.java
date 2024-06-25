package hello.advance.proxy.interfaceproxy;

import hello.advance.proxy.v1_proxy.*;
import hello.advance.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceConfig {
    @Bean
    public OrderControllerinterfaceproxy orderControllerinterfaceproxy(LogTrace logTrace){
        OrderControllerV1 orderControllerV1=new OrderControllerV1Impl(orderServiceInterfaceproxy(logTrace));
        return new OrderControllerinterfaceproxy(orderControllerV1,logTrace);
    }
    @Bean public OrderServiceInterfaceproxy orderServiceInterfaceproxy(LogTrace logTrace){
        OrderServiceV1 orderServiceV1=new OrderServiceV1Impl(orderRepoInterfaceProxy(logTrace));
        return new OrderServiceInterfaceproxy(orderServiceV1,logTrace);
    }

    @Bean public OrderRepoInterfaceProxy orderRepoInterfaceProxy(LogTrace logTrace){
        OrderRepoV1 orderRepoV1=new OrderRepoV1Impl();

        return new OrderRepoInterfaceProxy(orderRepoV1,logTrace);
    }
}
