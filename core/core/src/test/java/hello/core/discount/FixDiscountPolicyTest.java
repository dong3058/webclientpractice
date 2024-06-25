package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixDiscountPolicyTest {

    RateDiscountPolicy discountpolicy=new RateDiscountPolicy();


    @Test
    void vip_o(){
        Member member= new Member(1L,"mem", Grade.VIP);
        int discount=discountpolicy.discount(member,10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }



}