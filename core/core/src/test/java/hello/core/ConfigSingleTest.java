package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigSingleTest {

    @Test
     void Tests() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl m1 = ac.getBean("memberService", MemberServiceImpl.class);

        OrderServiceImpl m2 = ac.getBean("orderService", OrderServiceImpl.class);

        System.out.println(m1.getMemberRepository().hashCode());
        System.out.println(m2.getMemberRepository().hashCode());
        Assertions.assertThat(m1.getMemberRepository()).isSameAs(m2.getMemberRepository());
    }//전부다 같은애들. 즉 싱글톤이 보장됨.
    //또한 체크용 call 도 3번만 호출된다 왜지?

    @Test
    void configtest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //위 과정으르 class로 넘기면 앱콘피그ㅗ 스프링 빈에 등록됨.
        AppConfig bean=ac.getBean(AppConfig.class);
        System.out.println(bean.getClass());
        //class hello.core.AppConfig$$SpringCGLIB$$
        //순수한 클래스면 appconfig까지만 나오는게 맞는대 스프링이 만들어버린 클래스이다.
        //cglib이라는 바이트코드 조작 라이브러리로 다른애를 만들어버린거다.
        //이 조작한 애를 appconfig로 등록한것.
        //이게 싱글톤이 되도록 보장한다.
        //애를 이용해서 처음에는 스프링 컨테이너에 멤버리포지토리가없으므로 새로생성해서
        //등록을하고 이것을 계속해서 재사용하는것.
        //또한 cglib이붙은 앱콘피그는 실제로 앱콘피그의 자식타입으로 우리가 조회화면 이게나온다.
        //appconfig에 @configuration을안붙여도 컨테이너에 등록은되나 싱글톤이 보장이안된다.
        //즉 의존관계주입이 필요해서 메서드를 직접호출할떄 싱글톤 보장이안된다는말.
        // 즉 cglib이 아니라 appconfig가 나옴.
        //즉 멤버 리포지토리가 각각 다른 인스턴스가 나오게된다.



    }

}
