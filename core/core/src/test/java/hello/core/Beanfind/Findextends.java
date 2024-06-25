package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Findextends {

    //BeanFactory 최상위 컨테이너이며 인터페이스이며 getbean을 제공함.
    // 이 아래에 ApplicationContext라는 인터페이스가 빈팩토리를 상속받음.
    // 그러나 애플리케이션 컨텍스트는 많은걸 상속받는다.쨋든 둘중하나를 스프링 컨테이너라고하며 보통 상속받은 기느이많은
    //애플리케이션 컨텍스트를 사용하게될것이다.
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);



    @Test

    //오류터짐 왜냐 자손이 2개니까. 즉 모두가 걸려온다는말.
    void findbypar(){

        DiscountPolicy bean=ac.getBean("ratediscount",DiscountPolicy.class);



    }


    @Configuration
    static class TestConfig{
    @Bean
    public DiscountPolicy ratediscount(){
        return new RateDiscountPolicy();
    }

    /*@Bean
    public DiscountPolicy fixdiscount(){

        return new FixDiscountPolicy();
    }*/




    }
}
