package hello.advance.threadlocal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {
    private String namestore;

    public void logic(String name){
        log.info("저장 name={}->namestore={}",name,namestore);
        namestore=name;
        sleep(1000);
        log.info("조회 namestroe={}",namestore);
        ;
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
