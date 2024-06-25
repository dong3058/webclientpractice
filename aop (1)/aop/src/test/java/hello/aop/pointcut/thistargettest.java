package hello.aop.pointcut;


import hello.aop.Member.MemberService;
import hello.aop.Member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Member;

@Slf4j
@Import(thistargettest.thistargetscuess.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
//@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class thistargettest {

    //this target 둘다 부모 타입이 허용된다.

    @Autowired
    MemberService memberService;


    @Test
    void sucesss(){
        log.info("memberservice proxy={}",memberService.getClass());
        memberService.hello("helloa");
    }

    @Slf4j
    @Aspect
    static class thistargetscuess {
        //부모타입 허용
        @Around("this(hello.aop.Member.MemberService)")
        public Object dothisinterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("this-interface:{}", joinPoint.getSignature());
            return joinPoint.proceed();
        }


            //부모타입 허용
            @Around("target(hello.aop.Member.MemberService)")
            public Object dothisinterface2(ProceedingJoinPoint joinPoint) throws Throwable {
                log.info("target-interface:{}", joinPoint.getSignature());
                return joinPoint.proceed();
            }



            //부모타입 허용
            @Around("this(hello.aop.Member.MemberServiceImpl)")
            public Object dothisinterfaceimpl1(ProceedingJoinPoint joinPoint) throws Throwable {
                log.info("this-impl:{}", joinPoint.getSignature());
                return joinPoint.proceed();
            }


            @Around("target(hello.aop.Member.MemberServiceImpl)")
            public Object dothisinterfaceimpl2(ProceedingJoinPoint joinPoint) throws Throwable {
                log.info("target-impl:{}", joinPoint.getSignature());
                return joinPoint.proceed();
                //애는 결과가 안나온다. 왜냐하면 인터페이스 기반 프록시의 경우
                // memberserice를 구현한 프록시를 만들뿐이지 구현체인 impl과ㅏ는 아무 관련이없기떄문.
                //지금은 jdk 동적프록시이기에 안되는건대 위의 조건을 바꿔서 cglib로 바꾸면 됨.
            }

    }
}
