package hello.advance.trace.hellotrace.v0;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryv0 {


    public void save(String itemid){
        if(itemid.equals("ex")){
            throw new IllegalStateException("예외발생!");
        }
        sleep(1000);
    }


    public void sleep(int millis){
        try{
        Thread.sleep(millis);}
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
