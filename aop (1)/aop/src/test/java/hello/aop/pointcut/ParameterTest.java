package hello.aop.pointcut;

import hello.aop.Member.MemberService;
import hello.aop.Member.annotation.ClassAop;
import hello.aop.Member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void sucess(){
        log.info("membeserice.proxy ={}",memberService.getClass());
        memberService.hello("helloa");
    }

    @Slf4j
    @Aspect

    static class ParameterAspect{
        @Pointcut("execution(* hello.aop.Member..*.*(..))")
        private void allMember(){

        }
        @Around("allMember")
        public Object logsArgs1(ProceedingJoinPoint joinPoint) throws Throwable{
            Object args1=joinPoint.getArgs()[0];
            log.info("[logargs1]{},args={}",joinPoint.getSignature(),args1);
            //넘긴 인수 체크가능.
            return joinPoint.proceed();
        }
        @Around("allMember &&args(arg,..)")
        public Object logsArgs2(ProceedingJoinPoint joinPoint,Object arg) throws Throwable{
              //args의 경우 메서드가 받는 인자 이름이랑 이름을 같게해줘야돔 arg가 같은거서럼.
            //또한 메서드에 정해준 arg의타입을 따라감 즉 (arg,..)은 (Object arg,..)과 동치.
            log.info("[logargs2]{},args={}",joinPoint.getSignature(),arg);
            //넘긴 인수 체크가능.
            return joinPoint.proceed();
        }

        @Before("allMember()&&args(arg,..)")
        //이경우 맨앞의 인수가 string인걍우만 받는다는것.
        public void logArgs3(String arg){
            log.info("[logargs2] arg={}",arg);
        }
        @Before("allMember()&&this(obj)")
        public void this1(ProceedingJoinPoint joinPoint,MemberService obj){
            //this target둘다 인수에 membeserive를 붙엿으므로 애가 obj로 들어온다.
            //특히 애는 스프링 컨테이너에 올라온애 즉 proxy가 해당된다.
            log.info("[this]{},obj={}",joinPoint.getSignature(),obj.getClass());
        }
        @Before("allMember()&&target(obj)")
        public void target1(ProceedingJoinPoint joinPoint,MemberService obj){
            //애는 실제 구현체 즉 프록시를 호출할 대상을 의미
            log.info("[this]{},obj={}",joinPoint.getSignature(),obj.getClass());
        }


        @Before("allMember()&& @target(annotation)")
        public void target2(ProceedingJoinPoint joinPoint, ClassAop annotation){
            //애는 실제 구현체 즉 프록시를 호출할 대상을 의미
            log.info("[@target]{},obj={}",joinPoint.getSignature(),annotation);
        }

        @Before("allMember()&& @within(annotation)")
        public void within(ProceedingJoinPoint joinPoint, ClassAop annotation){
            //@target within 모두 어노테이션 정보를 가져오온다.
            log.info("[@within]{},obj={}",joinPoint.getSignature(),annotation);
        }
        @Before("allMember()&& @annotation(annotation)")
        public void annotation(ProceedingJoinPoint joinPoint, MethodAop annotation){
            //@annotation으니 메서드에 붙은 어노테이션을 받음. 또한 대입된 값을 가져오는걸 볼수가있따.
            log.info("[@annotation]{},obj={}",joinPoint.getSignature(),annotation.value());
        }
    }
}
