package jwt.jwtoken;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {
    private final JwtUtill jwtUtill;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("jwt필터작동:{}",servletRequest);
        String token=resolvetoken((HttpServletRequest) servletRequest);
        if(token!=null&&jwtUtill.validatetoken(token)){

            Authentication authentication= jwtUtill.getauth(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }




    public String resolvetoken(HttpServletRequest req){
        String token=req.getHeader("Authorization");
        if(StringUtils.hasText(token) &&token.startsWith("Bearer")){
            return token.substring(7);
        }
        return null;
    }
}
