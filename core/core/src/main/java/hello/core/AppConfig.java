package hello.core;
//생성과 기능의 구분이다.생성은 app config가 하는것.
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class AppConfig {

//싱글톤이라매요 멤버서비스 호출하면 new멤머레포지토리잔아요??? 뭐임 ㅅㅂ;?
    @Bean
    public MemberService memberService(){


        System.out.println("call memberserice");
        return new MemberServiceImpl(memberrepository());
    }
    //본디 함수의 인자로 new MemberREPository 가들어가는건대 아래 함수로 역할을 분리했음.
    @Bean
    public MemberRepository memberrepository(){
        System.out.println("call memberrepo");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){

        System.out.println("call orderrserice");
        return new OrderServiceImpl( memberrepository(),discountpolicy());

    }
    //애도 마찬가지.
    @Bean
    public DiscountPolicy discountpolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        //진정한의미에서의 객체지향이 실현?되었다.
    }
}
