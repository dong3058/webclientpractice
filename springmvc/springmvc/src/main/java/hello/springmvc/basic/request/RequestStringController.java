package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestStringController {

    @PostMapping("request-body-string-v1")
    public void requestbodystring(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ServletInputStream inputstream=req.getInputStream();
        //스트림은 바이트 코드이기떄문에 뭘로바꿀지를 항상 설정해줘야된다.
        String msgbody= StreamUtils.copyToString(inputstream, StandardCharsets.UTF_8);
        log.info("msgbody={}",msgbody);

        resp.getWriter().write("ok");
    }

    @PostMapping("request-body-string-v2")
    public void requestbodystring2(InputStream inputstream, Writer respwriter)
            throws IOException {

        //이렇게 설정할수도잇따.
        //인풋스트림-> 요청의 바디내용 직접 조회
        //outputsream 요청의 바디내용 직접 출력.
        String msgbody=StreamUtils.copyToString(inputstream, StandardCharsets.UTF_8);
        log.info("msgbody={}",msgbody);

        respwriter.write("ok");
    }

    @PostMapping("request-body-string-v3")
    public HttpEntity<String> requestbodystring3(HttpEntity<String> httpEntity)
            throws IOException {
        //HttpEntity는  헤더,바디의 내용을 직접 접근한다.
        //요청 파라미터를 조회하는 기능과는 관계가없다. @equestparam,modelattribute이런거
        //즉 form,get방식이ㅡ 경우에는 @requestparam modelattribute를 쓰지만
        //그외의것들은 이걸못씀. 또한 view 조회를 하지않고 헤더정보도 포함이가능.
        //스프링 mvc내부에서 바디의 내용을 읽어서 문자열 혹은 객체로 자체적으로 돌려준다
        //이걸 메시지 컨버터라고 한다. 나중에 이야기할것이다.
        String msgbody=httpEntity.getBody();
        log.info("msgbody={}",msgbody);

        return new HttpEntity<>("OK");
    }
    @PostMapping("request-body-string-v32")
    public HttpEntity<String> requestbodystring32(RequestEntity<String> httpEntity)
            throws IOException {

        String msgbody=httpEntity.getBody();
        log.info("msgbody={}",msgbody);

        return new ResponseEntity<String>("OK", HttpStatus.CREATED);
        //이런것도잇는대 이것도 어차피 httpentity를 상속받은것이다.
        //req엔티티으경우 메서드 url정보가 추가된다.
        //resp엔티티의경우 상태코드가 설정이 가능하다.
    }
    @ResponseBody
    @PostMapping("request-body-string-v4")
    public String requestbodystring4(@RequestBody String msgbody)
            throws IOException {
        //어노테이션을 이용한 단축버전. requestbody를 이용해서 읽는 다음에
        //string으로 msgbody에 넘겨준다.
        //g헤더가 필요하면 httpentity나 @responseheader를 쓰자.
        //@Responsebody 도 존재하느대 http바디에 내용을 직접담는다.
        //또한 view는 조회를 하지않는다.
        log.info("msgbody={}",msgbody);

        return "ok";
    }
    //어디까지나 파라미터 조회와 바디내요을 읽어오는 본문의 내용은 별개의 기능이다.
    //헷갈리지말자.

    //hhtmlㅣ같은걸 생성 해서 반환하느게 아닌 즉 뷰를 거치지않고  응답메시지에
    //직접 뭔가를 넣을떄 사용하는것이 바로 메시지컨버터이다.
    //이컨버터는 요청에대해서 해당요청을 적절한 인수로 바꾸던가 혹은 응답에대해서
    //return으로 돌려줄때 받는이가 원하는 형태 즉 accpet에 적힌 형태로 돌려주는
    //역할을 한다.
    //이미 스프링이 등록된 컨버터에는 바이드,스트링,오브젝트 라는 3가지가 기본적으로 존재하며
    //요청 응답에대해서 이 3가지를 차레때로 돌리면서 처리할수있는 컨버터를 찾고 작동하는것이
    //매커니즘이다.
    //특히 요청 데이터의 경우 contenttpye을 고려하고
    //응답의경우에는 accpet헤더를 고려해서 진앻한다.


    //arguemt resolver 애는 requestmapping의 어댑터가 호출하는애인대.
    //htpp요청--->해당되는 컨트롤러---->애의 어댑터-->argumentresolver로
    //진행되는 꼴이고 이 argumentresolver가 핸들러가 필요로 하는 파라미터를 생성하는 역할을 한다.
    //특히나 requestebody 혹은 httpentity같은경우 이 argument resolver에서
    //http 메시지 컨버터를 실행해서 파라미터를 가져오게된다.


    //또한 이는 return즉 돌려줄떄에도 똑같이  return value handler를 통해서 원하는
    // 파라미터를 형성하고 응답에 적용이된다.
    //특히 responsebody 혹은 httpentiy 형태의 return은 return vale handler를 통해서
    //http메시지 컨버터를 불렁고 이를 처리한다.
}
