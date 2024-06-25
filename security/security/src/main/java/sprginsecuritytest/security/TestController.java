package sprginsecuritytest.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
public class TestController {


    @GetMapping("/api1")
    public String api1(){
        return "api1";
    }


    @GetMapping("/api2")
    public String api2(){
        return "api2";
    }


    @GetMapping("/user")
    public String user(HttpServletRequest req){
        log.info("user sessionid:{}",req.getSession().getId());
        Authentication a= SecurityContextHolder.getContext().getAuthentication();
        log.info("userid:{}",a.getPrincipal());
        log.info("userpassword:{}",a.getCredentials());
        //log.info("sessionid:{}",req.getSession(false).getId());
        log.info("user auth:{}",a.getAuthorities());

        return "user";


    }



}
