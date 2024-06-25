package hello.advance.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServicelocalTest {
    private FieldLocalService fieldService=new FieldLocalService();

    @Test
    void field(){
        log.info("main start");
        Runnable usera=()->{
            fieldService.logic("usera");
        };
        Runnable userb=()->{
            fieldService.logic("userb");
        };

        Thread threada=new Thread(usera);
        threada.setName("A");

        Thread threadb=new Thread(userb);
        threadb.setName("B");

        threada.start();

        //sleep(2000);
        sleep(100);
        threadb.start();
        sleep(3000);
        log.info("main extit");

    }


    private void sleep(int mill){
        try{
            Thread.sleep(mill);
        }
        catch(InterruptedException e){
            e.printStackTrace();

        }
    }
}
