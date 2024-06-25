package hello.proxy.concreateproxy;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreateLogic {
    public String opreation(){
        log.info("concreatelogic 실행");
        return "data";
    }
}
