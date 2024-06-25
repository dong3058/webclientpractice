package sprginsecuritytest.security;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Passwordencoder {
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
}
