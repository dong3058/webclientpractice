package jwt.jwtoken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TestService {

    private final TestRepository testRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtill jwtUtill;
    private final SignDetailService signDetailService;

    public void save(Sign sign){
        testRepository.save(sign);
    }


    public Optional<Sign> find(Sign sign){
        return testRepository.findsign(sign.getUsername());
    }

    public JwtToken signin(String username,String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);

        Authentication authentication=authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        log.info("testservice auth check:{} {} {}",authentication.getPrincipal(),authentication.getCredentials(),authentication.getAuthorities());

        return jwtUtill.genjwt(authentication);
    }
}
