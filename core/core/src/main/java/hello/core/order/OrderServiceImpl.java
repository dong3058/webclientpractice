package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

public class OrderServiceImpl implements OrderService{



    //private final MemberRepository memberRepository;//=new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy ;//=new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy =new RateDiscountPolicy();
    //private DiscountPolicy discountPolicy;
    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;

    //@Autowired//setter 주입. 우선 오더서비ㅡ 임플을 등록하고나서 연관관계가잇는애들을 주입.
    //생성자 주입은 객체 생성과동시에 주입이되나 애는 생성자가없이 setter를 호출해서 주입해야됨.
    //requried false로 선택적으로 주입시 이용가능`
    /*public void setMemberRepository(MemberRepository memberRepository) {
            System.out.println("m"+memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("d:"+discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    //@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountprice=discountPolicy.discount(member,itemPrice);


        return new Order(memberId,itemName,itemPrice,discountprice);

    }


    public MemberRepository getMemberRepository(){
        return  memberRepository;
    }
}
