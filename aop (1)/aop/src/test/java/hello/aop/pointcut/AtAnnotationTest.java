package hello.aop.pointcut;


import hello.aop.Member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@Import(AtAnnotationTest.annotationaspect.class)
@SpringBootTest
public class AtAnnotationTest {

    @Autowired
    MemberService memberService;


    @Test
    void sucess(){
        log.info("memberservice proxy={}",memberService.getClass());
        memberService.hello("helloa");
    }

    @Slf4j
    @Aspect
    static class annotationaspect{
        @Around("@annotation(hello.aop.Member.annotation.MethodAop)")
        //메서드에 @methodapp이 붙는애들을 대상으로 삼는 포인트컷.
        //w즉 어떤 클래스든간에 내부 메서드에 저게 달리면 실행하겟다(methodaop)라는말.
        //@args는 전달된 인수에 ㅇ떤어노테이션이 달려잇는경우 즉 전달된 인수가 클래스인대
        //그안에 어노테이션이 달린경우를 의미함.
        public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[@annotation] {}",joinPoint.getSignature());
            return joinPoint.proceed();
        }

    }
}
