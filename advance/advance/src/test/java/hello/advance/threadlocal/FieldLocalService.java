package hello.advance.threadlocal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLocalService {
    private ThreadLocal<String> namestore=new ThreadLocal<>();

    public String logic(String name){
        log.info("저장 name={}->namestore={}",name,namestore.get());
        namestore.set(name);
        sleep(1000);
        log.info("조회 namestroe={}",namestore.get());

        return namestore.get();
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
