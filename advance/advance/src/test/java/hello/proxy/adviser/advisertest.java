package hello.proxy.adviser;

import hello.proxy.Cglib.ServiceImpl;
import hello.proxy.Cglib.ServiceInterface;
import hello.proxy.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.DefaultAdvisorChainFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.awt.*;
import java.lang.reflect.Method;
@Slf4j
public class advisertest {

    @Test
    void advisortest1(){
        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);
        DefaultPointcutAdvisor advisor=new DefaultPointcutAdvisor(Pointcut.TRUE,new TimeAdvice());
        //항상 true를바노한하는 포인트컷과 어드바이스를 넣음.
        proxyFactory.addAdvisor(advisor);

        //이전에 addadvice를써서 한것도 파고들어가보면 어드바이저가 형성되어있따.
        ServiceInterface proxy=(ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }
    @Test
    void advisortest2(){
        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);
        //DefaultPointcutAdvisor advisor=new DefaultPointcutAdvisor(Pointcut.TRUE,new TimeAdvice());
        DefaultPointcutAdvisor advisor=new DefaultPointcutAdvisor(new mypointcut(),new TimeAdvice());
        //항상 true를바노한하는 포인트컷과 어드바이스를 넣음.
        proxyFactory.addAdvisor(advisor);

        //이전에 addadvice를써서 한것도 파고들어가보면 어드바이저가 형성되어있따.
        ServiceInterface proxy=(ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }

    @Test
    void advisortest3(){
        ServiceInterface target=new ServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);
        NameMatchMethodPointcut pointcut=new NameMatchMethodPointcut();
        pointcut.setMappedName("save");
        DefaultPointcutAdvisor advisor=new DefaultPointcutAdvisor(pointcut,new TimeAdvice());
        //항상 true를바노한하는 포인트컷과 어드바이스를 넣음.
        proxyFactory.addAdvisor(advisor);

        //이전에 addadvice를써서 한것도 파고들어가보면 어드바이저가 형성되어있따.
        ServiceInterface proxy=(ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }
    static class mypointcut implements Pointcut{
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }
    static class MyMethodMatcher implements MethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result=method.getName().equals("save");
            log.info("포인트컷 호출 method={},tragetclass={}",method.getName(),targetClass);
            log.info("포인트컷 결과 result={}",result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
