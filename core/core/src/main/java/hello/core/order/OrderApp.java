package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.Grade;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLOutput;

public class OrderApp {
    public static void main(String[] args) {


        //AppConfig appconfig=new AppConfig();

        //MemberService memberservice=appconfig.memberService();
        //OrderService orderservice =appconfig.orderService();

       //MemberService memberservice=new MemberServiceImpl();
        //OrderService orderservice =new OrderServiceImpl();
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberservice=applicationContext.getBean("memberService",MemberService.class);
        OrderService orderservice=applicationContext.getBean("orderService",OrderService.class);
        //ApplicationContext 를 스프링 컨테이너라고하는대 애는 인터페이스다.
        //new AnnotationConfigApplicationContext(AppConfig.class); 이게 ApplicationContext의 구현체이다.
        //빈 이름을 각각 다르게 해야됨.
        //일종의 앱 콘피그에서 메서드이름을 키값으로 value값으로  MemberSerice.class를 가진다고 보면된다
        Long memberId=1L;

        Member member = new Member(memberId,"memberA", Grade.VIP);

        memberservice.join(member);

        Order order =orderservice.createOrder(memberId,"itemA",10000);


        System.out.println("order="+order);
        System.out.println("order="+order.calculatePirce());

    }
}
