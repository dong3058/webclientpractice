package jwt.jwtoken;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
@Slf4j
@Component
public class JwtUtill {
    @Value("${jwt.secret}")
    private  String key;

    @Value("${jwt.expiration}")
    private  Long expiration;





    public JwtToken genjwt(Authentication authentication){
        SignDetail signDetail=(SignDetail) authentication.getPrincipal();
        String claim=authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accesstokne= Jwts.builder()
                .claim("auth",claim)

                .setSubject(signDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();

        String refreshtoken=Jwts
                .builder()
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();

        return JwtToken.builder()
                .accesstoken(accesstokne)
                .refreshtoken(refreshtoken)
                .grantType("Bearer")
                .build();


    }

    public Authentication getauth(String token) {
        Claims claims = getclaims(token);

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth")
                        .toString().split(","))
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
        String username=claims.getSubject();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(username,"",authorities);


        return usernamePasswordAuthenticationToken;

    }

    public boolean validatetoken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }


    public Claims getclaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
