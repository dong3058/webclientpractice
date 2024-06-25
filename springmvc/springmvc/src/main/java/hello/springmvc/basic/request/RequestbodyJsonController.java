package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloDate;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestbodyJsonController {
    private ObjectMapper objectMapper=new ObjectMapper();


    @PostMapping("/request-body-json-v1")
    public void reqbodyjson1(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ServletInputStream inputstream=req.getInputStream();
        String msgbody= StreamUtils.copyToString(inputstream, StandardCharsets.UTF_8);
        log.info("msgbody={}",msgbody);
        HelloDate hellodata=objectMapper.readValue(msgbody,HelloDate.class);
        log.info("username={},age={}",hellodata.getUsername(),hellodata.getAge());

        resp.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String reqbodyjson2(@RequestBody String msgbody) throws IOException {


        log.info("msgbody={}",msgbody);
        HelloDate hellodata=objectMapper.readValue(msgbody,HelloDate.class);
        log.info("username={},age={}",hellodata.getUsername(),hellodata.getAge());

        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String reqbodyjson3(@RequestBody HelloDate data)  {
        //리퀘스트 바디에다가 객체를 넘길수도있다.
        //httpentitiy 리퀘스트 바디는 http메시지 컨버터가 바디의 내용을 원하는 문자열
        //혹은 객체로 바꿔준다. 또한 json도 객체로 잘바꿔준다.이과정에서 v2에서 json을 객체로 바꾼 과정을 자동으로해준다.
        // 만일 리퀘스트 바디를 생략할경우 객체이므로 modelattribtue가 붙는다.
        //스프링은 reqparam modelattribute 제외시 단순 타입은 reqparam
        //객체는 모델 어트리뷰트를 붙인다.
        //따라서 reqbody생략시 요청 파라미터를 처리하게된다.
        //추가적으로 객체에 세터 게터나 정의 되지않으면
        //int는 0이 들어가고 string은 ""이들어간다.

        log.info("username={},age={}",data.getUsername(),data.getAge());

        return "ok";
    }


    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String reqbodyjson4(HttpEntity<HelloDate> httpEntity)  {

        HelloDate data=httpEntity.getBody();// httpenitiy가 제네릭이기에
        //겟 바디로 꺼내면 제네릭 타입으로 나오게된다.
        log.info("username={},age={}",data.getUsername(),data.getAge());

        return "ok";
    }



    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloDate reqbodyjson5(@RequestBody HelloDate data)  {

        log.info("username={},age={}",data.getUsername(),data.getAge());

        return data;
        //responsebody가 있다면 http메시지 컨버터가 들어올떄,나갈떄에도 적용이된다.
        //정확히는 requestbody 에의해서 http msg=>json->객체
        //객체->json->http msg 이렇게 대입이된다. 즉 해당객체를 body에 직접넣는게
        //가능하다.
        //참고로 어떤 컨버터 즉 return을 어던 형태로줄지는 reqest의 accpet이라는
        //헤더가 결정한다.
    }
}
