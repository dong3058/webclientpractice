package hello.core;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(//애가 컴포넌트 붙은 클래스를 다찾아서 싱글톤으로등록. 이떄 빈의 이름을 앞글자가 소문자인 클래스명으로등록.
        //basePackages = "hello.core.member",//탐색 시작위치 여러개를 원하면{"anme',"name"}이런꼴.
        // 만약 등록하지않으면 componentscan이 붙은애를 기준으로 ㅌ마색 그러므로 애를 최상위에두면 편하다.
        // 이름을 직ㅈ버 지정하고싶으면 @Compoenent(name)이런식으로 해주자.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)
)//기존 앱콘피그가 configuration이 잇기에 컴포넌트 스캔 대상이된다. 즉 수동과 자동이 충돌하므로제외시킴.
// 이외에도 스캔 대상엔 컨트롤러,서비스,레포지토리 도 탐색대상이다.(@에 영어붙은건대 귀찬아서 한글로씀.)
// 사실 어노테이션은 상속기능이없다 즉 @configuration을 인식하는건 스프링의 기능이지 자바의 기능이아니다.
//즉 자바 클래스에 @config를 붙여서 애가 설정임을 안다는것은 스프링의 기능인셈이다.
//자동등록시 같은 빈이름이 여러개있으면 오류발생.
//수동빈.자동빈 충돌시 수동빈이 우선권. 즉 수동빈이 자동빈을 오버라이딩해버림.버그는 아닌대 이게 의도한 경우는 거의없다.
//즉 오류는 아니지만 오류로 보고싶ㅇㄴ 그런???친구???
//그래서 최근 스프링 부트는 자동등록과 수동등록 충돌시 오류가 나도록 바꿔버림 즉 @bean(name)에서 name을 같에하고
// 해보면 오류가난다.

//의존관계주입
//1. 생성자주입:autowired를 사용한것. 생성자 호출시점에 딱 1번만호출되는게 보장됨.불편,필수과정에서 쓰임.
//단 생성자가 딱한개만 있다먼 autowired를 않넣어도 같은 효과를 준다. bean이든 componentscan으로 등록하든.
//2.setter주입: 선택적인애들임.autowired(required=False)를 해주자. 그러면 필수가아니므로 필요할떄등록하면됨.
//3.필드주입: 말그대래ㅗ 필드에대가 autowired를 붙이는것. private임에도가능...

//4. 일반메서드 주입 일반 메서드에더가 autwired를 써버린느것. 생성자 주입과같은대 일반 메서드 버전이라고보면된다.

public class AutoAppConfig {

}
