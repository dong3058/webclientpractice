package hello.aop.proxyvs;

import hello.aop.Member.MemberService;
import hello.aop.Member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class PorxyCastingTest {

    @Test
    void jdkPorxy(){
        MemberService target=new MemberServiceImpl();
        ProxyFactory proxyFactory=new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);


        MemberService proxy=(MemberService)proxyFactory.getProxy();

        MemberServiceImpl proxy2=(MemberServiceImpl)proxyFactory.getProxy();
        //jdk는 meberserive라는 인터페이스를 구현한 프록시를 반환하지 impl은 모름.
        //즉 구체클래스로 캐스팅이 불가능하다
        //그러나 cglib는 impl은 memeberserivce의 구현체이고 proxy는 impl을 상속받는다
        //즉 둘다가능.
    }
}
