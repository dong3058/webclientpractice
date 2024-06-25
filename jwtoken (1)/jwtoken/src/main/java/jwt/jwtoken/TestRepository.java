package jwt.jwtoken;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;
    public void save(Sign sign){
        sign.setRole("user");
        em.persist(sign);

    }

    public Optional<Sign> findsign(String username){
        Optional<Sign> s=Optional.ofNullable(em.find(Sign.class,username));
        return s;
    }


}
