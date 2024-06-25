package hello.thymleaf.basic;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data","hello");
        //값을 <b> hello <b>로입력해도 th:text 라던ㄷ가 [[~~]]는 http엔티티를 제공한다.
        //즉 특수기호를 그냥 문자열 그대로 인식해버리게하는게 http엔티티이다.
        //즉 html 태그로 인식하지않는다이말이다. 이걸 이스케이프라고한다.
        return "basic/text-basic";
    }


    @GetMapping("text-unescaped")
    public String textunescaped(Model model){
        model.addAttribute("data","hello");
        //값을 <b> hello <b>로입력해도 th:text 라던ㄷ가 [[~~]]는 http엔티티를 제공한다.
        //즉 특수기호를 그냥 문자열 그대로 인식해버리게하는게 http엔티티이다.
        //즉 html 태그로 인식하지않는다이말이다. 이걸 이스케이프라고한다.
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User usera=new User("userA",10);
        User userb=new User("userB",20);

        List<User> list=new ArrayList<>();
        list.add(usera);
        list.add(userb);

        Map<String,User> map=new HashMap<>();
        map.put("userA",usera);
        map.put("userB",userb);

        model.addAttribute("user",usera);
        model.addAttribute("users",list);
        model.addAttribute("usermap",map);

        return "basic/variable";
    }
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessiondata","hello session");


        return "basic/basic-objects";
    }
    @Component("/hellobean")
    static class HelloBean{
        public String hello(String data){
            return "Hello"+data;
        }
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1","Data1");
        model.addAttribute("param2","Data2");

        return "basic/link";
    }


    @GetMapping("/literal")
    public String literal(Model   model){

        model.addAttribute("data","spring");

        return "basic/literal";


    }

    @GetMapping("/operation")
    public String operation(Model model){

        model.addAttribute("nulldata",null);
        model.addAttribute("data","spring");

        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model){



        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){

        addusers(model);


        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model){

        addusers(model);


        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comment(Model model){
        model.addAttribute("data","spring");

        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model){
        addusers(model);


        return "basic/block";


    }

    @GetMapping("/javascript")
    public String js(Model model){

        model.addAttribute("user",new User("usera",50));
        addusers(model);


        return "basic/javascript";


    }



    private void addusers(Model model){
        List<User> list=new ArrayList<>();
        list.add(new User("usera",200));
        list.add(new User("userb",300));
        list.add(new User("userc",400));
        model.addAttribute("users",list);
    }


    @Getter
    @Setter
    static class User{
        private String  username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
