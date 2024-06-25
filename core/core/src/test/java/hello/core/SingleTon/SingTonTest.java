package hello.core.SingleTon;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SingTonTest {



    @Test

    void pureContainer(){
        AppConfig appConfig=new AppConfig();


        MemberService memberService1=appConfig.memberService();

        MemberService memberService2=appConfig.memberService();


        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    //당연하게도 서로다른 객체이다. 즉요청이 들어올떄마다 수많은 갹체가 생성,삭제된다는의미.
    //싱글톤 패턴은 클래스의 인스턴스를 딱 1개만 만들게하는 패턴이다.
    }
    @Test

    void Singtontest(){

        SingleTonPattern service1=SingleTonPattern.getInstance();
        SingleTonPattern service2=SingleTonPattern.getInstance();

        Assertions.assertThat(service1).isSameAs(service2);//한개의 객체 생성.

    }
    @Test

    void singleContainer(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

        //스프링 컨테이너는 자체적으로 컨테이너 형성시 beans 를 등록해서 단한개의 객체만을 저장한다.
        // 즉 컨테이너에 등록한 bean는 싱글톤을 따라간다는것이다.
        MemberService memberService1=ac.getBean("memberService", MemberService.class);
        MemberService memberService2=ac.getBean("memberService", MemberService.class);
        //MemberService memberService1=appConfig.memberService();

       // MemberService memberService2=appConfig.memberService();


        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        //당연하게도 서로다른 객체이다. 즉요청이 들어올떄마다 수많은 갹체가 생성,삭제된다는의미.
        //싱글톤 패턴은 클래스의 인스턴스를 딱 1개만 만들게하는 패턴이다.
    }
//싱글톤 패턴의 주의사항

 //싱글톤으니 한개의 개체를 생성해서 사용하므로 이 개체가 특정 필드값이 크라이언트 의존성이 잇으면안된다.
 //클라이언트가 값을 변경할수있게 하면안되며 될수있는한 읽기만 가능해야된다.



}
