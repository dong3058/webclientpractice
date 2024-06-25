package jwt.jwtoken;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final JwtUtill jwtUtill;

    @GetMapping("/authfail")
    @ResponseBody
    public String authfail(){
        return "authfail";
    }
    @GetMapping("/login")
    @ResponseBody
    public String logins(){
        return "loginget";
    }
    @GetMapping("/loginfail")
    @ResponseBody
    public String loginfail(){
        return "loginfail";
    }

    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestBody Sign sign){
        testService.save(sign);
        return "ok";
    }

    @PostMapping("/login")
    @ResponseBody
    public JwtToken login(@RequestBody Sign sign){
        Optional<Sign> s=testService.find(sign);
        if(s.isPresent()){
            Sign sign1=s.get();

            return testService.signin(sign1.getUsername(),sign1.getPassword());

        }
        return null;
    }

    @GetMapping("/api1")
    @ResponseBody
    public String api1(){
        return "api1";
    }
    @GetMapping("/api2")
    @ResponseBody
    public String api2(){
        return "api2";
    }



}
