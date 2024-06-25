package hello.aop.exam.internalcall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1){
        log.info("callservice v1 :{}", callServiceV1.getClass());
        this.callServiceV1=callServiceV1;
    }

    public void external(){
        log.info("call external");
        callServiceV1.internal();//이거는 this.interanl 즉 객체내부의 함수로 프록시를 거치지않으.
    }
    public void internal(){
        log.info("call internal");
    }
}
