package hello.advance.proxy.dynamic_proxy;


import hello.advance.proxy.v1_proxy.*;
import hello.advance.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicConfig {

    @Bean
    public OrderRepoV1 orderRepoV1(LogTrace logTrace){
        OrderRepoV1 orderRepoV1=new OrderRepoV1Impl();
        OrderRepoV1 proxy=(OrderRepoV1) Proxy.newProxyInstance(OrderRepoV1.class.getClassLoader(),new Class[]{OrderRepoV1.class},new LogTraceHandler(orderRepoV1,logTrace));
        return proxy;
    }
    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace){
        OrderServiceV1 orderServiceV1=new OrderServiceV1Impl(orderRepoV1(logTrace));
        OrderServiceV1 proxy=(OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),new Class[]{OrderServiceV1.class},
                new LogTraceHandler(orderServiceV1,logTrace));
        return proxy;

    }

    @Bean
    OrderControllerV1 orderControllerV1(LogTrace logTrace){
        OrderControllerV1 orderControllerV1=new OrderControllerV1Impl(orderServiceV1(logTrace));
        OrderControllerV1 proxy=(OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),new Class[]{OrderControllerV1.class},
                new LogTraceHandler(orderControllerV1,logTrace));


        return proxy;
    }
}
