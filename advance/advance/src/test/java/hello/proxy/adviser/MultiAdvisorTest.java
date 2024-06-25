package hello.proxy.adviser;

import hello.proxy.Cglib.ServiceImpl;
import hello.proxy.Cglib.ServiceInterface;
import hello.proxy.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
@Slf4j
public class MultiAdvisorTest {

    @Test
    void mulitadvisortest1(){

        DefaultPointcutAdvisor advisor=new DefaultPointcutAdvisor(Pointcut.TRUE,new advice1());
        DefaultPointcutAdvisor advisor2=new DefaultPointcutAdvisor(Pointcut.TRUE,new advice2());




        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);

        proxyFactory.addAdvisor(advisor2);//한개에다가 넣을떈 어드바이저 넣는 순서진해오딤.

        proxyFactory.addAdvisor(advisor);


        //이렇게 한개의 프록시로 여러개의 어드바이저 즉 논리를 실행이가능.


        ServiceInterface proxy=(ServiceInterface) proxyFactory.getProxy();

        proxy.save();



    }





    static class advice1 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출");
            return invocation.proceed();
        }


    }
    static class advice2 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출");
            return invocation.proceed();
        }


    }
}
