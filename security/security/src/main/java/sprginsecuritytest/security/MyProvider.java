package sprginsecuritytest.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Component
@Slf4j

public class MyProvider implements AuthenticationProvider {



    private final MemberService memberService;

    public MyProvider(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("내가만든 provider 작동중");
        String userid=(String) authentication.getPrincipal();
        String password=(String) authentication.getCredentials();

        log.info("provider 인증 객체 체크:{} {} {}",userid,authentication.getAuthorities(),password);
        UserDetails m=memberService.loadUserByUsername(userid);
        log.info("멤버서비스에서 가쟈온 비밀번호 {} {}",m.getPassword(),m.getAuthorities());
        //!passwordEncoder.encode(m.getPassword())

        if(!m.getPassword().equals(password)){
            log.info("비밀번호 미일치 에러발생");
            throw new CredentialsExpiredException("비밀번호 미일치");
        }
        log.info("권한 확인:{}비밀번호확인:{}",m.getAuthorities(),m.getPassword());
        log.info("멀티체크 {}",m.getAuthorities());
        log.info("내가만든 provider 작동끝");
        return new UsernamePasswordAuthenticationToken(userid,password,m.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
