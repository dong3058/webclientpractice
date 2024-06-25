package hello.core.Scan;


import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//autowired는 타입 매칭 시도후 여러빈이있다면 필드이름,파라미터 이름으로 빈을 찾는다.
//즉 discountPolicy 로등록된 빈의 경우 필드이름중에 애랑 일치하는애를 가져온다 라는것이다.
//orderserviceimpl의 생정자의 파라미터 참고.
//@qulifier("name")참고로 이건 빈이름을 추가하는게 아니라 추가적인 검색조건을 넣는것이다.
//이걸 생서자에 넣어서 검새조건을 추가할수가있다.오더임플 참조. 단점이라면  주입되는곳에 이걸 붙여야된다. 즉 귀찬다.
//필드,수정자에도가능하다.
//만일 퀄리파이어로도 못찾으면 그내부의 이름을 가지는 스프링빈을 찾는다.
//직접 빈등록시에도 이용가능.
//@primary 여러개의 것이 빈에서 걸리면 프라이머리붙은애가 최우선권을 가진다. 퀄리파이어의 우선순위가 프라이머리보다 높다.
public class AutoAppConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService=ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
