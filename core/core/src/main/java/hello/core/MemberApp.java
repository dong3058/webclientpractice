package hello.core;

import hello.core.member.Grade;

import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appconfig=new AppConfig();
        //MemberService memberService=appconfig.memberService();
        //MemberService memberService= new MemberServiceImpl();


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        //bean에  메서드 이름으로 등록된걸 가져온다는말.

        Member member=new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);
        Member findMember=memberService.findMember(1L);
        System.out.println("new member="+member.getName());
        System.out.println("find Member="+findMember.getName());

        

    }

    @Configuration
    @ComponentScan(
            excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes=Configuration.class)
    )
    public static class AutoAppConfig {
    }
}
