package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Aimpl implements  Ainterface{

    @Override
    public String call() {
        log.info("a호출");
        return "a";
    }
}
