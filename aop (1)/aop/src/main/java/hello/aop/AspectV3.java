package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {


    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){}//포인트컷 시그니처 public으로 바꾸면 단대서도 실행이가능하.

    @Pointcut("execution(* *..*Service.*(..))")
    public void AllService(){};



    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinpoint) throws Throwable{
        log.info("log {}",joinpoint.getSignature());
        return joinpoint.proceed();
    }

    @Around("allOrder() && AllService()")//두개의 범위를 만족하는애들을 찾는다.
    public Object doTransaction(ProceedingJoinPoint joinpoint) throws Throwable{
        try{
            log.info("트랜잭션시작 {}",joinpoint.getSignature());
            Object result=joinpoint.proceed();
            log.info("트랜잭션 커밋 {}",joinpoint.getSignature());
            return result;
        }
        catch(Exception e){
            log.info("트랜잭션 롤백 {}",joinpoint.getSignature());
            throw e;
        }
        finally{
            log.info("리소스 릴리즈 {}",joinpoint.getSignature());
        }
    }
}
