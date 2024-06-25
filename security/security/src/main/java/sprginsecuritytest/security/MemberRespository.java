package sprginsecuritytest.security;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRespository {

    private final EntityManager em;
    //private final Passwordencoder passwordencoder;
    public void save(Member member){
        log.info("password:{}",member.getPassword());
        //member.setPassword(passwordencoder.getBCryptPasswordEncoder().encode(member.getPassword()));
        em.persist(member);

    }

    public Member findmember(String id){
        return em.find(Member.class,id);
    }



}
