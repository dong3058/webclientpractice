package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloDate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/requset-param-v1")
    public void requestParamv1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username=req.getParameter("username");
        int age=Integer.parseInt(req.getParameter("age"));
        log.info("username={} age={}",username,age);

        resp.getWriter().write("ok");
    }

    @ResponseBody//view 조회를 무시하고 다이렉트로 body에적는다.
    @RequestMapping("/request-param-v2")
    public String RequestParamv2(@RequestParam("username") String username,
                                 @RequestParam("age") int age){


        log.info("username={} age={}",username,age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String RequestParamv3(@RequestParam String username,
                                 @RequestParam int age){


        log.info("username={} age={}",username,age);
        return "ok";

    }
    @ResponseBody
    @RequestMapping("/request-param-required")
    //required=true 쿼리에 필수적으로 들어가야한다는소리이다.방향으로 필수라고 정의하는것.
    // false일떄 쿼리에 넣지않으면 기본값이 들어간다.
    //이때 null이들어가는대 int의경우에는 null을 못쓴다.
    //그러므로 integer로 정의를해서 null을 받게하자.
    //또한 파라미터name="" 이러헥 공백으로 넣으면 빈문자열로 들어간다.
    public String RequestParamvrequired(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) int age){


        log.info("username={} age={}",username,age);
        return "ok";

    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    //기본값 설정.
    public String RequestParamvdefault(@RequestParam(defaultValue="z") String username,
                                        @RequestParam(defaultValue="-1") int age){


        log.info("username={} age={}",username,age);
        return "ok";

    }





    @ResponseBody
    @RequestMapping("/request-param-map")
    //맵으로 한꺼번에 받기.
    //MulitValueMap도 가능함. 이것은  한개의 키에 배열 형태로 여러개의 값을 받는것임.
    public String RequestParamvamp(@RequestParam Map<String,Object> paramMap){


        log.info("username={} age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";

    }
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttribute(@ModelAttribute HelloDate hellodata){

        /*HelloDate hellodata=new HelloDate();
        hellodata.setUsername(username);
        hellodata.setAge(age);*/
        //이렇게 생략이 가능.
        //모델 어트리뷰트는 객체를 생성하고 parame네임에 해당되는 게터세터
        //즉 username이면 Username이라는 세터로 저장을한다.
        //model attribute생략하고 객체를 써도되긴한다.
        //string int integer같은 단순타입은 @rquestparam 으로
        // 그외의것은 모델어트리뷰트로 접근한다(argumentresolver제외)
        log.info("username={} age={}",hellodata.getUsername(),hellodata.getAge());
        return "ok";
    }



}
