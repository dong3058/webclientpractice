package sprginsecuritytest.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class MemberDetail implements UserDetails {

    private Member member;
    public MemberDetail(Member member){
        this.member=member;
    }


    private List<SimpleGrantedAuthority> authorities=new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String auth="ROLE_"+ member.getRole();
        if(authorities.size()==0){
        authorities.add(new SimpleGrantedAuthority(auth));}
        log.info("권한 확인 in detail:{}",member.getRole());
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
