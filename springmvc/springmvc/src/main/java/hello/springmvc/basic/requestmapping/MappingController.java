package hello.springmvc.basic.requestmapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log= LoggerFactory.getLogger(getClass());
    @RequestMapping("/hello-basic")//({x1,x2})이렇게 2개가가능.
    //method타입을 다로 지정하지않는이상 무슨 req든간에 반응한다.
    public String hellobasic(){

        log.info("hellobasic");
        return "ok";
    }
    /**
     * 편리한 축약 메서드
     * Get,Post,Delte 든 뭐든 니가상상한 뭐든있다.
     *GetMapping
     */
    @GetMapping("/mapping/{userid}")//requestmapping은 url을 템플릿화할수있따.
    //이거는 url경로에 값이 들어간경우.
    //이걸 pathvaribale로 써내쓴것.
    //@PathVariable("userid")String data
    //아레처렴 변수명을 일치시키면 생략도가능.
    public String mappingGetv2(@PathVariable String userid){
        //log.info("mappingPath userid={}",data);
        log.info("mappingPath userid={}",userid);

    }

    @GetMapping("/mapping/users/{userid}/orders/{orderid}")
    public String mappigpath(@PathVariable String userid,@PathVariable int orderid){
        log.info("userid={} orderid={}",userid,orderid);

        return "ok";
    }
    //이렇게 여러개 변수도 가능.

    /**
     * parmas="mode"
     * params="!mode"
     * params={"mode=debug","data=good"}
     * */
    @GetMapping(value="/mapping/param",params="mode=debug")
    //url 에 mode=debug가있어야 됨.
    public String mappingparam(){
        log.info("mappingparam");
        return "ok";
    }
    //이거과 가은 버전으로 params 만 headers로바꾼 즉 헤드에 뭐가있어야만
    //파싱하는그런것도있음.


    /** contenttype 기반 매핑.
     * consumes = "!application/json"
     * "!application/*"
     * "*\/*" 이런것들도있따.
     * MediaType.APPLICATION_JSON_VALUE
     * @return
     */
    //consumes는 요청헤더의 컨탠트 타입 기반으로  미디어타입으로 매핑.
    //produces는 요청헤더의 accpet기반으로  미디어타입으로매핑
    @PostMapping(value="/mapping-consume",consumes = "application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "0k";


    }

    /**
     * 애는 accpet헤더가 필ㅇ함.
     * 그외의 나머지는 위의것과 같음.
     * MediaType.TEXT_HTML_VALUE 같은것도 가능.
     * @return
     */
    //컨텐트 타입와 accpet의차이는 컨탠트타입은 그냥
    //이러이러한 데이터로 보내갰다고 말을하는것
    //즉 서버 클라이언트간에 나이런꼬로 보낼꺼다 를 말하는거고
    //accpet은 클라가 서버에게 나 클라이언트는 이런 데이터만 받을거다 라고
    //말하는것이다.
    @PostMapping(value="/mapping-consume",produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "0k";


    }
}
