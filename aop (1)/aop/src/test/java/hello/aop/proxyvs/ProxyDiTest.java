package hello.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
public class ProxyDiTest {

    @Before("execution(* hello.aop..*.*(..))")
    public void doTrace(JoinPoint joinPoint){
        log.info("[proxydiadvice] {}",joinPoint.getSignature());
    }
}
