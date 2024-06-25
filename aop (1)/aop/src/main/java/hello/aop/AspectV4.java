package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
//어드바이스의 순서는 @aspect다단ㅇ위로 @order를 통해서 정해짐
//즉 안ㅇ있는 두개의 순서를 정하고싶으면 각각 따로 클래스르 만들어줘야된다는말.
public class AspectV4 {


    @Around("hello.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinpoint) throws Throwable{
        log.info("log {}",joinpoint.getSignature());
        return joinpoint.proceed();
    }

    @Around("hello.aop.Pointcuts.orderAndService()")//두개의 범위를 만족하는애들을 찾는다.
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
