package hello.aop.exam.internalcall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external(){
        log.info("call external");
        internal();//이거는 this.interanl 즉 객체내부의 함수로 프록시를 거치지않으.
    }
    public void internal(){
        log.info("call internal");
    }
}
