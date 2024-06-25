package hello.proxy.Cglib;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreateService {
    public void call(){
        log.info("concrecatesrvice 호출");
    }
}
