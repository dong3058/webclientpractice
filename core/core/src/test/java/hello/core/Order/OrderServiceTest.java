package hello.core.Order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberservice;//=new MemberServiceImpl();
    OrderService orderservice ;//=new OrderServiceImpl();





    @Test
    void createOrder(){
        long memberId=1L;

        Member member=new Member(memberId,"memberA", Grade.VIP);
        memberservice.join(member);
        Order order=orderservice.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
