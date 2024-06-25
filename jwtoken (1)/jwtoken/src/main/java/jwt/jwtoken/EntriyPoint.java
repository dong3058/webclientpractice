package jwt.jwtoken;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Slf4j
public class EntriyPoint implements AuthenticationEntryPoint {
    private RequestCache requestCache=new HttpSessionRequestCache();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("엔트리포이느 핸들러작동 로그인 페이지로 이동.");
        requestCache.saveRequest(request,response);
        response.sendRedirect("/login");

    }
}
