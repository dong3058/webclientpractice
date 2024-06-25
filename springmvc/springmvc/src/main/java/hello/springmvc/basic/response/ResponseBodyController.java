package hello.springmvc.basic.response;


import hello.springmvc.basic.HelloDate;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody 가각 붙이는게 귀찬음년 이렇게 클래스레벨에다가
//한번에떄려박자.
//근대 컨트롤러랑 리스폰스 바디 합친게 restcontroller임.
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void respbody1(HttpServletResponse resp) throws IOException {
        resp.getWriter().write("ok");
    }



    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> resp2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String resp3(){
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloDate> jsonresp1(){
        HelloDate hellodata=new  HelloDate();
        hellodata.setUsername("userA");
        hellodata.setAge(20);


        return new ResponseEntity<>(hellodata,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)// response body가있으면 지정못하는 상태코드를 작성하는것.
    //단 동적인 걸원하면 entity를 써야된다.
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public  HelloDate jsonresp2(){
        HelloDate hellodata=new  HelloDate();
        hellodata.setUsername("userA");
        hellodata.setAge(20);


        return hellodata;
    }
}
