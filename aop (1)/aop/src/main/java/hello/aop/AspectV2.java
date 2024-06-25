package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {


    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){}//포인트컷 시그니처 public으로 바꾸면 단대서도 실행이가능하.



    @Around("allOrder()")
    public Object dolog(ProceedingJoinPoint joinpoint) throws Throwable{
        log.info("log {}",joinpoint.getSignature());
        return joinpoint.proceed();
    }


}
