package hello.aop.pointcut;
import hello.aop.Member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest {
    @Autowired
    Child child;
    @Test
    void success() {
        log.info("child Proxy={}", child.getClass());
        child.childMethod(); //부모, 자식 모두 있는 메서드
        child.parentMethod(); //부모 클래스만 있는 메서드
    }
    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }
        @Bean
        public Child child() {
            return new Child();
        }
        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();
        }
    }
    static class Parent {
        public void parentMethod(){} //부모에만 있는 메서드
    }
    @ClassAop
    static class Child extends Parent {
        public void childMethod(){}
    }
    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect {
        //@target: 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 적용
        @Around("execution(* hello.aop..*(..)) && @target(hello.aop.Member.annotation.ClassAop)")
        //뒤으 문장은 classaop어노테이션이 잇는지 여부를 따지는것.
                public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
                log.info("[@target] {}", joinPoint.getSignature());
                return joinPoint.proceed();
                }
                //@within: 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용 되지 않음


        //즉 둘다 어노태이션 기반인대 부모까지 되냐 안되냐 그차이이다. 또한 둘다 단독으로 사용응ㄴ 하면안됨.
        //@args도 포함되는 내용이다.
        //왜냐면 단독으로 사용시에는 final 이붙은 상속이 불가능한 클래스들은 프록시 생성이 불가능한다
        //이런 것떄문에 오류가 날수도있다.
        //args,@target args within 는 객체의 인스턴스가 생성되고 런타임이 될떄 어드바이스 적용 여부가
        //체크가 가능하다. 이러한 포인트컷 적용 여부도 결국 프록시가 실행시점에 잇어야 구분이가능.
        //그러니까 이러한 적용 여부를 판별하기위해서 스프링은 모든 애들에대해서 프록시를 만ㄷ ㄹ려고
        //시도를 하는대 이떄 final이 붙은 ㅇ애들은 그게 안되므로 오류가 발생한다.
                @Around("execution(* hello.aop..*(..)) && @within(hello.aop.Member.annotation.ClassAop)")
                public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
                log.info("[@within] {}", joinPoint.getSignature());
                return joinPoint.proceed();
                }
    }
}