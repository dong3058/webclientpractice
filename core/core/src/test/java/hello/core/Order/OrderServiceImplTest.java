package hello.core.Order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository mem=new MemoryMemberRepository();
        mem.save(new Member(1L,"name", Grade.VIP));
        OrderServiceImpl orderService=new OrderServiceImpl(mem,new FixDiscountPolicy());
        orderService.createOrder(1L,"itemA",10000);
        //순수한 자바코드로 테스트 즉 생성자 주입을 하면 이렇게 슬수가잇다. 또한 fianl 키워드를 슬수가잇다.
        //생성자 주입이면 누락 즉 인자를 빠트려먹은걸 알수가있으며 final 키워드 즉 생성과 동시에 초기화가 되므로
        //final을 쓸수가있게된다.
        //생성 자 호출말고 나머지는 객체이후에 주입되므로 final이 사용이불가하다.
        //프레임워크에 의존하지낭ㅎ고 자바그대로 쓸수가잇게된다.
    }

}