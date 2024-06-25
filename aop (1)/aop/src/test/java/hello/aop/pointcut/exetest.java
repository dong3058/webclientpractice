package hello.aop.pointcut;


import hello.aop.Member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class exetest {
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
        pointcut.setExpression("execution(public String hello.aop.Member.MemberServiceImpl.hello(String))");
        //전부다 맞춰줘야됨 매칭조건-> 접근제어지,반환타입 선언타입--->hello.aop.Member.MemberServiceImpl 이걸의미 메서드이름 파라미터 예외(생략)
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void allmatch(){
        pointcut.setExpression("execution(* *(..))");
        //가장많이 생략한것 반환타입 메서드 이름을 * 로대체하고 파라미터는 ..로 바꿈.
        //..는 파라미터 타입,수가 상관이없다 잇든 없든 여러개든 무곤.
        //접근제어자 같은건 생략해도됨. 선언타입도 생략한것.
        //즉 간단한게 말해서 반환 타입 메서드이름이 * 즉 모든 함수에 대해서 실행 한다는말.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void namematch(){
        pointcut.setExpression("execution(* hello(..))");
        //애는 반환 타입무곤하게 ehllo이름 바밭응로 찾늗나.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void namematch2(){
        pointcut.setExpression("execution(* hel*(..))");
        //애는 반환 타입무곤하게 ehllo이름 바밭응로 .
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }    @Test
    void namematch3(){
        pointcut.setExpression("execution(* *llo(..))");
        //애는 반환 타입무곤하게 ehllo이름 바밭응로 찾늗나.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }    @Test
    void namematch4(){
        pointcut.setExpression("execution(* *e*(..))");
        //애는 반환 타입무곤하게 메서드 이름 앞뒤에 * 넣는게 가능.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packagematch(){
        pointcut.setExpression("execution(* hello.aop.Member.MemberServiceImpl.hello(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }

    @Test
    void packagematch2(){
        pointcut.setExpression("execution(* hello.aop.Member.*.*(..))");
        //* hello.aop.*.*(..)은 오류가발생
        //즉이건 member 패키지까지 탐색을 하지않고 aop바로아래의 클래스들만 찾는다는말.
        //즉정확히 aop직속의 클래스들만 찾는다는말.
         Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }
    @Test
    void packagematchsercepackage(){
        pointcut.setExpression("execution(* hello.aop.Member..*.*(..))");
        //hello aop member와 그 하위의 모든 패키지를의미
        //즉 위에서 한 * hello.aop.*.*(..)가 오류가 뜨는 이유느,ㄴ aop.이라고 aop에 정확히 매칭되는 애들만
        //찾기떄문. 즉"."은정확히 해당 패ㅣ지 ..은 해당과 그 하위 모두포함.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }

    @Test
    void typeexactmatch(){
        pointcut.setExpression("execution(* hello.aop.Member.MemberServiceImpl.*(..))");

        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

    }
    @Test
    void typeexactmatchsupertype(){
        pointcut.setExpression("execution(* hello.aop.Member.MemberService.*(..))");

        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();

        //부모 타입이여도 멤버 서비스impl이므로 가능!
        //즉 부모 타입 선언해도 자식타입은 매칭됟나.

    }

    @Test
    void typeexactmatchinternal() throws NoSuchMethodException{
        pointcut.setExpression("execution(* hello.aop.Member.MemberService.*(..))");
        Method internal=MemberServiceImpl.class.getMethod("internal",String.class);
        Assertions.assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isTrue();
        //애는 false 즉 자식타입만의고유한 메서드는 매칭되지않음. 왜냐면 부모 타입에 존재하지않으니가.
        //즉 인터페이스에 선어된것만 매칭.

    }

    @Test
    void argsmatch() throws NoSuchMethodException{
        pointcut.setExpression("execution(* *(String))");

        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();


    }

    @Test
    void argsmatch2() throws NoSuchMethodException{
        pointcut.setExpression("execution(* *())");
        //거짓 왜냐면 hellomethod는 파라미터가 없으니까.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();


    }

    @Test
    void argsmatch3() throws NoSuchMethodException{
        pointcut.setExpression("execution(* *(*))");
        //애는 모든 메서드의 모든 args 다 가능. 단 애는 * 이 의미하는건 한개의 args만을의미
        //만일 args 갯수에 상관없이면 ..을넣자.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();


    }
    @Test
    void argsmatch4() throws NoSuchMethodException{
        pointcut.setExpression("execution(* *(String,..))");
        //애는 첫번쨰 인수가 String이고 나머지는 상관없다.
        //string2개를 원하면 String 두번쓰자.
        //혹은 인수가 딱 두개인대 앞의것만 String이고 두의 변수는 상관없으면 string,*이다.
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();


    }





}
