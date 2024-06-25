package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
public class FindSameName {


    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(SameBean.class);
    @Test
    void findByName(){


        ac.getBean(MemberRepository.class);

    }//애는 ㅡ오류 왜냐 같은 타입이 2개가있기대문. 즉 부모 클래스의 인스턴스가 2개 존재시에는 전부다 끌려나온다는말.



    //이름 정해주면 오류 x.
    @Test
    void findByName2(){


        ac.getBean("memberRepository",MemberRepository.class);

    }

    @Test//같은 타입 여러개 꺼내오기. 추가적으로 부모클래스를 조회시에는 모든 자식클래스가달려나온다.작 부모빈 탐색이 자식빈을 끌고나옴.
    void findByName3() {

    Map<String,MemberRepository> beansOfType=ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key=" + key + "value=" + beansOfType.get(key));
        }
        System.out.println("beansoftype:"+beansOfType);

    }







    @Configuration
    static class SameBean{

        @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }



    }
}
