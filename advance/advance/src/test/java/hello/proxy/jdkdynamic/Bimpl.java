package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bimpl implements  Binterface{

    @Override
    public String call() {
        log.info("B호출");
        return "b";
    }
}
