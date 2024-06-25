package hello.advance.proxy.classproxy;

import hello.advance.proxy.v2_proxy.OrderControllerV2;
import hello.advance.proxy.v2_proxy.OrderRepoV2;
import hello.advance.proxy.v2_proxy.OrderServiceV2;
import hello.advance.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassProxyConfig {
    @Bean
    public  OrderControllerClassProxy orderControllerClassProxy(LogTrace logTrace){
        OrderControllerV2 orderControllerV2=new OrderControllerV2(orderServiceClassProxy(logTrace));
        return new OrderControllerClassProxy(orderControllerV2,logTrace);
    }
    @Bean
    public  OrderServiceClassProxy orderServiceClassProxy(LogTrace logTrace){
        OrderServiceV2 orderServiceV2=new OrderServiceV2(orderRepoClassProxy(logTrace));
        return new OrderServiceClassProxy(orderServiceV2,logTrace);
    }
    @Bean
    public  OrderRepoClassProxy orderRepoClassProxy(LogTrace logTrace){
        OrderRepoV2 orderRepoV2=new OrderRepoV2();
        return new OrderControllerClassProxy(orderRepoV2,logTrace);
    }
}
