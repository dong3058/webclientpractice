package hello.springmvc.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//여기서쓰는 ㄹ그는 스프링 부트를 이용하면 자동적으로 깔리는 로그 라이브러리이다.
//여기선 slf4j를 구현한 logback을 쓴다. 참고로 스프링부ㄴ트느 로그백을 기본제공함.
//@controller 는 반환값이 string이면 뷰이름으로 인식하고 뷰를찾고
//뷰를 랜더링한다.

//그러나 레스트 컨트롤러는 반환값으로 뷰를 찾는게 아니라http메세지에 바디에 따로입력함.
public class LogTestController {

    private final Logger log= LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name="Spring";
        System.out.println("name="+name);
        log.trace("tarace log={}",name);//각각 로그레벨을 정해주는것.
        log.debug("tarace log={}",name);
        log.info("info log={}",name);
        log.warn("tarace log={}",name);
        log.error("info log={}",name);
        //아래로갈수록 로그레벨이 상승. 즉 debug를 출력하면 트레이스뺴고 출력.
        //보고싶은 레벨 은 appliction porpertis에서 설정할수가있다.
        //로그 사용시규칙
        //log.debug("tarace log={}"+name);
        //이건 스면안된다. 왜냐면 자바 특징상 안의 연산을 먼저실행
        //즉 trace log={}spring이런꼴로 바뀌게돈다.
        //출력은 되지도않는대 연산은 다해버리는 즉 자원낭비가발생한다.


        return "ok";

    }
}
