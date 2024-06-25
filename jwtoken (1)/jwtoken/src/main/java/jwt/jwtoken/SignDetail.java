package jwt.jwtoken;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SignDetail implements UserDetails {

    private Sign sign;
    private List<SimpleGrantedAuthority> simpleGrantedAuthorityList=new ArrayList<>();
    public SignDetail(Sign sign) {
        this.sign = sign;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role="ROLE_"+sign.getRole();
        if(simpleGrantedAuthorityList.size()==0){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role));

        }
        return simpleGrantedAuthorityList;
    }


    @Override
    public String getPassword() {
        return sign.getPassword();
    }

    @Override
    public String getUsername() {
        return sign.getUsername();
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
