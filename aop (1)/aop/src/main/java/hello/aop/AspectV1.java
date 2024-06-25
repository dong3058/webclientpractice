package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Aspect
public class AspectV1 {


    @Around("execution(* hello.aop.order..*(..))")
    public Object dolog(ProceedingJoinPoint joinpoint) throws Throwable{
        log.info("log {}",joinpoint.getSignature());
        return joinpoint.proceed();
    }
}
