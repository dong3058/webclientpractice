package hello.advance.proxy.factory;

import hello.advance.proxy.v1_proxy.OrderRepoV1;
import hello.advance.proxy.v1_proxy.OrderRepoV1Impl;
import hello.advance.proxy.v1_proxy.OrderServiceV1;
import hello.advance.proxy.v1_proxy.OrderServiceV1Impl;
import hello.advance.trace.logtrace.LogTrace;
import lombok.extern.java.Log;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ProxyConfigV1 {

    @Bean
    public OrderRepoV1 orderRepoV1(LogTrace logTrace){
        OrderRepoV1 orderRepoV1=new OrderRepoV1Impl();
        ProxyFactory proxyFactory=new ProxyFactory(orderRepoV1);

        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderRepoV1 proxy=(OrderRepoV1) proxyFactory.getProxy();
        return proxy;
    }
    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace){
        OrderServiceV1 orderServiceV1=new OrderServiceV1Impl(orderRepoV1(logTrace));
        ProxyFactory proxyFactory=new ProxyFactory(orderServiceV1);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV1 proxy=(OrderServiceV1) proxyFactory.getProxy();

        return proxy;
    }


    private Advisor getAdvisor(LogTrace logTrace){
        NameMatchMethodPointcut pointcut=new NameMatchMethodPointcut();
        pointcut.setMappedNames("request","order","save");

        LogTraceAdvice logTraceAdvice=new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut,logTraceAdvice);
    }
}
