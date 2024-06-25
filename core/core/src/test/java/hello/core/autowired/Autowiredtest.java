package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;


public class Autowiredtest {

    @Test
    void  AutoWiredOption(){


    }
    //대부분의 주입관계는 한번일어나면 불변이다. 앱 종료시점까지 의존곤계변경이거의없다.
    //생성자 주입은 객체 생성시 딱 한번만 호출된다. 그 이후엔 호출되지아니한다.
    static class TestBean{
        @Autowired(required=false)//의존관계가없을떄 이ㅏ예호출이 안됨 true 즉 default면 오류발생.
        public void setnobeal1(Member nobean1){
            System.out.println("nobean1:"+nobean1);
        }
        @Autowired
        public void setnobeal2(@Nullable Member nobean2){//호출은 되는대 null로나옴.왜냐 없는 빈이니까.
            System.out.println("nobean2:"+nobean2);
        }
        @Autowired
        public void setnobeal1(Optional<Member> nobean3){//없는 빈이면 optional.empty값을 넣어줌
            System.out.println("nobean3:"+nobean3);
        }
    }
}
