package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ResponseviewController {
    //정적 리소스의 경우 그냥 url에다가 ㅏ파일경로를적어주면 애가 알아서 찾아간다 ㅇ;
    //  url앞부분/basic/hello 이런식이다.
    @RequestMapping("/response-view-v1")
    public ModelAndView responseviewv1(){

        ModelAndView mav= new ModelAndView("response/hello")
                .addObject("data","hello!");


        return mav;
    }


    @RequestMapping("/response-view-v2")
    public String responseviewv2(Model model){

        model.addAttribute("data","ehllo");

        return "response/hello";//view의 논리적 이름을 반환.
        //이러면 이경로의 뷰가 랜더링된다.
        //리스폰스 바디가있으면 http메세지에 이 문자열이 박힌다.
    }

    @RequestMapping("/response/hello")
    public void responseviewv3(Model model){

        model.addAttribute("data","ehllo");

        //@controller를 쓰고 http 메세지바디를 처리하는 파라미터가 없으면(httpservlet outstream)
        //자동으로 로딩함.
    }
}
