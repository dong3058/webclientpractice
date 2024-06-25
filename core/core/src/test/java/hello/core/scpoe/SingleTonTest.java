package hello.core.scpoe;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

//프로토타입빈: 싱글톤을 유지하면 스프링컨테이너가 빈의 종료까지 애를 관리한다.
//그러나 프로토타입 빈은 매번 호출시 새로운 객체를 생성해서 클라이언트에 제공하며 스프릉 컨테이너는 이객체의 초기화단계까지만
//관리를하고 그이후이ㅡ 책임은 클라이언트에게 넘긴다. 그래서 종료메서드가 호출이안된다.
public class SingleTonTest {
    @Test
    void singletonBeanfind(){
        ConfigurableApplicationContext ac= new AnnotationConfigApplicationContext(SingletonBean.class);//사실 이렇게 클래스로넣으면 자동으로 컴포넌트 클래스됨.

        SingletonBean s1=ac.getBean("singleTonTest.SingletonBean",SingletonBean.class);
        SingletonBean s2=ac.getBean(SingletonBean.class);

        Assertions.assertThat(s1).isSameAs(s2);

        ac.close();//싱글톤이면 여기서 종로가 호출되다 프로토타입은 더이상 스프링컨테이너가 관리하지않기에
        //종료메서드가 호출이 안됨.
        //호출을 원한다면 직접 호출을해야된다.
    }

    @Scope("singleton")
    //@Scope("prototype")
    static class SingletonBean{

        @PostConstruct
        public void init(){
            System.out.println("bean.init");
        }
        @PreDestroy
        public void destory(){
            System.out.println("bean.destory");
        }


    }
}
