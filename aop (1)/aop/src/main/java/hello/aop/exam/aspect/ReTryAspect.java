package hello.aop.exam.aspect;

import hello.aop.exam.annotation.ReTry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class ReTryAspect {

    @Around("@annotation(retry)")
    public Object doReTry(ProceedingJoinPoint joinPoint, ReTry retry)throws  Throwable{

        log.info("[retry]:{} args:{}",joinPoint.getSignature(),retry);

        int max_try=retry.value();

        Exception exceptionholder=null;
        for(int retry_count=1;max_try>=retry_count;retry_count++){
            try{
                log.info("[retry] try count={}/{}",retry_count,max_try);
                return joinPoint.proceed();}
            catch(Exception e){
                log.info("error 발생 {}",retry_count);
                exceptionholder=e;

            }

        }

        throw exceptionholder;
    }
}
