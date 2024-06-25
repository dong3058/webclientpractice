package sprginsecuritytest.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
@Component
@Slf4j
public class FailHandler extends SimpleUrlAuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("error msg작동");
        String errorMessage="에러발생";
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMessage="아이디 또는 비밀번호가 맞지 않습니다.";
        }else if (exception instanceof UsernameNotFoundException){
            errorMessage="존재하지 않는 아이디 입니다.";
        }
        else{
            errorMessage="알 수 없는 이유로 로그인이 안되고 있습니다.";
        }
        /*errorMessage= URLEncoder.encode(errorMessage,"UTF-8");//한글 인코딩 깨지는 문제 방지
        request.setAttribute("error",errorMessage);*/
        redirectStrategy.sendRedirect(request,response,"/loginfail");
    }
}
