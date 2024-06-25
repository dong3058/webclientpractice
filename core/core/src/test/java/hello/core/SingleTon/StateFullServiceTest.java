package hello.core.SingleTon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

//싱글톤 패턴의 주의사항

//싱글톤으니 한개의 개체를 생성해서 사용하므로 이 개체가 특정 필드값이 크라이언트 의존성이 잇으면안된다.
//클라이언트가 값을 변경할수있게 하면안되며 될수있는한 읽기만 가능해야된다.
//즉 스프링 빈은 항상 무상태하게 설계하도록 하자.



class StateFullServiceTest {

    @Test

    void staefullservice(){
        ApplicationContext ac=new  AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService u1=ac.getBean("s",StateFullService.class);
        StateFullService u2=ac.getBean("s",StateFullService.class);

        u1.order("usera",10000);
        u2.order("userb",20000);


        int price=u1.getprice();

        Assertions.assertThat(price).isEqualTo(10000);//오류 즉 사용자1이
        //요청한 값이 달라졌다.
    }
    static class TestConfig {
        @Bean
        public StateFullService s() {
            return new StateFullService();
        }
    }
}