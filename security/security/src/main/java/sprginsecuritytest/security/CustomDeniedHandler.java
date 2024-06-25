package sprginsecuritytest.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import java.io.IOException;
@Slf4j
public class CustomDeniedHandler implements AccessDeniedHandler {
    private RequestCache requestCache=new HttpSessionRequestCache();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("인가 핸들러 작동");
        log.info("request uri:{}",request.getRequestURI());
        log.info("beforeurl:{}",request.getHeader("Referer"));
        requestCache.saveRequest(request,response);
        response.sendRedirect("/errorauth");
    }


}
