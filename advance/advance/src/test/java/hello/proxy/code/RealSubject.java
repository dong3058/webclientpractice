package hello.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements  Subject {
    @Override
    public String operation() {
        log.info("실제호출");
        sleep(1000);
        return "data";

    }
    private void sleep(int mill){
        try{
            Thread.sleep(mill);
        }
        catch(InterruptedException e){
            e.printStackTrace();;
        }
    }
}
