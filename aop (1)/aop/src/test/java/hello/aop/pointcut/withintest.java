package hello.aop.pointcut;


import hello.aop.Member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class withintest {
    AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();

    Method helloMethod;


    @BeforeEach
    public void init() throws NoSuchMethodException{
        helloMethod= MemberServiceImpl.class.getMethod("hello",String.class);
    }

    @Test
    void printmethod(){
        log.info("hello method={}",helloMethod);
    }
    @Test
    void exactmatch(){
        pointcut.setExpression("within(hello.aop.MemberServiceImpl)");
        //with in은 정확하게 타입이 같아야됨 즉 표현식에 부모타입이 들어가선안된다.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void exactmatch2(){
        pointcut.setExpression("within(hello.aop.Member.*Service*)");

        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }


    @Test
    void exactmatch3(){
        pointcut.setExpression("within(hello.aop..*)");
        //subpackge하는것.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }


    @Test
    void exactmatch4(){
        pointcut.setExpression("within(hello.aop.Member.MemberService)");
        //이건에러. 정확히 타입이 안맞기 떄문이다. 간단히 말해서 execution은 타입기반이고
        //within은 타겟의 타입에만 직접 적용이된다 그렇기에 범용성에서 execution이 앞선다.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }







}
