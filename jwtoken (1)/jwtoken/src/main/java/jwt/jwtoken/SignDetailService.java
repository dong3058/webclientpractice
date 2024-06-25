package jwt.jwtoken;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SignDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final TestRepository testRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Sign> s=testRepository.findsign(username);
        Sign sign = s.get();

        return createUserDetails(sign);
    }


    private UserDetails createUserDetails(Sign sign) {
        sign.setPassword(passwordEncoder.encode(sign.getPassword()));

        return new SignDetail(sign);
    }
}
