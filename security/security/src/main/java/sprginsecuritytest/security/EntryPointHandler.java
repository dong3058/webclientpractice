package sprginsecuritytest.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Slf4j
public class EntryPointHandler implements AuthenticationEntryPoint {

    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String uri=request.getRequestURI();
        RequestCache requestCache=new HttpSessionRequestCache();
        requestCache.saveRequest(request,response);
        log.info("엔트리 포인트 핸들러 작동 로그인 페이지로 이동합니다.");
        response.sendRedirect("/login");
    }
}
