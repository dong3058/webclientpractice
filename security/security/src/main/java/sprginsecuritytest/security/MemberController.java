package sprginsecuritytest.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    private final MemberService memberService;





    @PostMapping("/members/add")
    @ResponseBody
    public String memberassign(@ModelAttribute Member member){
       /* member.setRole("admin");
        log.info("check:{}",member.getPassword());*/
        memberService.MemberAssign(member);
        return "ok";
    }

    @GetMapping("/logins")
    @ResponseBody
    public String logins(){
        return "loginspage";
    }
    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestBody SignDto signDto){
        String username=signDto.getUsername();
        String password=signDto.getPassword();


        return "loginpage";
    }

    /*@PostMapping("/login")
    @ResponseBody
    public String memberlogin(@RequestBody Member member){
        log.info("memberemial:{}",member.getUsername());
        log.info("memberpassword:{}",member.getPassword());
        return "ok";
    }*/
    @GetMapping("/loginfail")
    @ResponseBody
    //@RequestParam(value="error") String errmsg
    public String faillogin(){
        return "loginerror";
    }

    @GetMapping("/")
    @ResponseBody
    public String passlogin(){
        //Authentication a=SecurityContextHolder.getContext().getAuthentication();
        //log.info("home check:{}{}{}",a.getCredentials(),a.getAuthorities(),a.getPrincipal());

        return "home";
    }

    @GetMapping("/errorauth")
    @ResponseBody
    public String authfial(HttpServletRequest req, HttpServletResponse resp){
        RequestCache requestCache=new HttpSessionRequestCache();
        log.info("url:{}",requestCache.getRequest(req,resp).getRedirectUrl());
        return "authfail";
    }


}
