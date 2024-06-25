package hello.aop.pointcut;

import hello.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import(beantest.BeanAspect.class)
public class beantest {
    @Autowired
    OrderService orderService;


    @Test
    void sucess(){
        orderService.orderitem("itema");
    }

    @Aspect
    static class BeanAspect{
        @Around("bean(orderService)||bean(*Repository)")
        //bean 이름으로 바타으로 진행되느걸로 spring 고유의것 즉 japsect에 속하지않음.
        public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable{
            log.info("[bean] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
