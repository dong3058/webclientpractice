package hello.advance.trace.hellotrace.v0;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepositoryv0 orderRepositoryv0;

    public void orderitem(String itemid){
        log.info("서비ㅅ 실행");
        orderRepositoryv0.save(itemid);
    }
}
