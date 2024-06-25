package sprginsecuritytest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
public class SucessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache=new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //log.info("성공 핸들러 작동 {}{}{} session check:{}",authentication.getPrincipal(),authentication.getCredentials(),authentication.getAuthorities());
        if(request.getSession(false)==null){
            log.info("nosession");
        }

        Authentication a=SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication data:{}{}{}",a.getCredentials(),a.getAuthorities(),a.getPrincipal());
        setDefaultTargetUrl("/");
        resultredirect(request,response,authentication);
    }


    protected void resultredirect(HttpServletRequest req,HttpServletResponse resp,Authentication authentication) throws IOException,ServletException{
        SavedRequest savedRequest=requestCache.getRequest(req,resp);

        if (savedRequest != null) {
            log.info("성공 1");
            log.info("beofre:{}",req.getHeader("referer"));
            log.info("savedreqest:{}",savedRequest.getRedirectUrl());
            String targetUrl = savedRequest.getRedirectUrl();
            requestCache.removeRequest(req,resp);
            redirectStrategy.sendRedirect(req,resp,targetUrl);

            //resp.sendRedirect("login/?redirectURL=/"+targetUrl);
        } else {
            log.info("성공 2");
            log.info("{}",getDefaultTargetUrl());
            redirectStrategy.sendRedirect(req,resp,getDefaultTargetUrl());

            //resp.sendRedirect("/");
        }
    }


}
