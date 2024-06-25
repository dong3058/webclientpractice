package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Applicationfindtest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
    @Test

    void findByName(){
    MemberService memberservice=ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberservice).isInstanceOf(MemberServiceImpl.class);



    }


    @Test

    void findByType(){
        MemberService memberservice=ac.getBean(MemberService.class);
        Assertions.assertThat(memberservice).isInstanceOf(MemberServiceImpl.class);



    }



    @Test

    void findByName2(){
        //구체적인 타입으로도 조회가가능한대 권장은 하지않음. 아래껀 리턴값인대 차피 다형성에의해서 포함관계니까 가능함.
        MemberService memberservice=ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberservice).isInstanceOf(MemberServiceImpl.class);



    }




}
