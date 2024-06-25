package jwt.jwtoken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache=new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.info("인증 성공 핸들러 작동");
        setDefaultTargetUrl("/");
        redirects(request,response);
    }



    public void redirects(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
        SavedRequest savedRequest =requestCache.getRequest(req,resp);
        if(savedRequest!=null){
            log.info("1번 루트발생");
            String url=savedRequest.getRedirectUrl();
            requestCache.removeRequest(req,resp);
            redirectStrategy.sendRedirect(req,resp,url);
        }
        else{
            log.info("2번루트");
            redirectStrategy.sendRedirect(req,resp,getDefaultTargetUrl());
        }


    }
}
