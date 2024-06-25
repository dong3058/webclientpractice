package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.MemberApp;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class AllBeanTest {


    @Test
    void findallbean(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(AutoAppConfig.class,Discount.class);
        Discount d1=ac.getBean(Discount.class);
        Discount d2=ac.getBean(Discount.class);

        Assertions.assertThat(d1).isSameAs(d2);//T싱글톤은 우선 보장은 되는듯???
        String [] names=ac.getBeanDefinitionNames();
        for(String name: names){
            System.out.println(name);
        }// discount도 추가가 된다. 단 애는 compoenetscan을 단다던가 혹은 configuration을 선언한게아니라서
        //그냥 등록만된것.

        Discount discounts =ac.getBean(Discount.class);

        Member member=new Member(1L,"usera", Grade.VIP);
        int discountable=discounts.discount(member,1000,"fixDiscountPolicy");
        Assertions.assertThat(discounts).isInstanceOf(Discount.class);
        Assertions.assertThat(discountable).isEqualTo(1000);

    }


    static class Discount{
        private final Map<String,DiscountPolicy> policymap;
        private final List<DiscountPolicy> policy;
        @Autowired
        public Discount(Map<String,DiscountPolicy> policymap, List<DiscountPolicy> policy){
            //autowirted의 경우 자동으로 빈이름으로 strong과 그빈값을 map에다가 넣어준다고한다.
            this.policymap=policymap;
            this.policy=policy;
            System.out.println("map"+this.policymap+"list"+this.policy);


        }
        public int discount(Member member,int price ,String discountcodes){
            DiscountPolicy discountpolicy=policymap.get(discountcodes);
            return discountpolicy.discount(member,price);
        }
    }

}
