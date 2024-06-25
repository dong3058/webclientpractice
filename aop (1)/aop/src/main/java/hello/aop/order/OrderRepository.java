package hello.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    public String save(String itemid){
        log.info("orderepo실행");
        if(itemid.equals("ex")){
            throw new IllegalStateException("오류발생");
        }
        return "ok";
    }
}
