package hello.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements  Subject{

    private Subject target;
    private String cahcheValue;

    public CacheProxy(Subject subject) {
        this.target=subject;
    }

    @Override
    public String operation() {
        log.info("프록시호출");
        if(cahcheValue==null){
            cahcheValue=target.operation();
        }
        return cahcheValue;
    }
}
