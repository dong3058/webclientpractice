package hello.proxy.advice;

import hello.proxy.Cglib.ConcreateService;
import hello.proxy.Cglib.ServiceImpl;
import hello.proxy.Cglib.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;


@Slf4j
public class ProxyFactoryTest {

    @Test
    void interfaceProxy(){
        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 사용할 부가기능을 넣어주는과정 즉 advice는 로직모음이라고새각하자.


        ServiceInterface proxy=(ServiceInterface) proxyFactory.getProxy();
        log.info("targetclass={}",target.getClass());
        log.info("proxyclass={}",proxy.getClass());

        proxy.save();

        AopUtils.isAopProxy(proxy);
        //프록시 팩토리쓸떄만 가능.aoputils가

    }
    @Test
    void concreateProxy(){
        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 사용할 부가기능을 넣어주는과정 즉 advice는 로직모음이라고새각하자.


        ConcreateService proxy=(ConcreateService) proxyFactory.getProxy();
        log.info("targetclass={}",target.getClass());
        log.info("proxyclass={}",proxy.getClass());

        proxy.call();

        AopUtils.isAopProxy(proxy);
        //프록시 팩토리쓸떄만 가능.aoputils가

    }
    @Test
    void proxytargetcalss(){
        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);

        proxyFactory.setProxyTargetClass(true);//항상 cglib를 만듬 target클래스를 기준으로.
        //즉 인터페이스가 있어도 클래스기반으로 강제로 만들어버린다는말.
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 사용할 부가기능을 넣어주는과정 즉 advice는 로직모음이라고새각하자.


        ConcreateService proxy=(ConcreateService) proxyFactory.getProxy();
        log.info("targetclass={}",target.getClass());
        log.info("proxyclass={}",proxy.getClass());

        proxy.call();

        AopUtils.isAopProxy(proxy);
        //프록시 팩토리쓸떄만 가능.aoputils가

    }
}
