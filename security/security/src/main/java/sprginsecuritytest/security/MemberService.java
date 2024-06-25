package sprginsecuritytest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRespository memberRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member=memberRespository.findmember(username);
        return new MemberDetail(member);
    }

    public void MemberAssign(Member member){

        memberRespository.save(member);
    }

    public void MemberAssignByJwt(SignDto signDto){


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(signDto.getUsername(),signDto.getPassword());

        Authentication authentication= authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);



    }
}
