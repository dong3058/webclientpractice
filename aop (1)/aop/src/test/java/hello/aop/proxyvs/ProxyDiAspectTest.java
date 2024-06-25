package hello.aop.proxyvs;


import hello.aop.Member.MemberService;
import hello.aop.Member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
@Import(ProxyDiTest.class)
public class ProxyDiAspectTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberServiceImpl membeServiceImpl;//에러발생 위에서jdk 동적 프록시로 프록시 생성ㅅ
    //캐스팅에 문제가생김. 오ㅔ냐면 우리가 프록시로 빈에 등록했기떄문.

    @Test
    void go(){
        log.info("memberservice class={}",memberService.getClass());
        log.info("membeservice impl class={}",membeServiceImpl.getClass());
        membeServiceImpl.hello("hello");
    }

}
