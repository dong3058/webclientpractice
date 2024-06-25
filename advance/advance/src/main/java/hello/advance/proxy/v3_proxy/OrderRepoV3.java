package hello.advance.proxy.v3_proxy;


import org.springframework.stereotype.Component;

@Component
public class OrderRepoV3 {
    public void save(String itemId) {
        if(itemId.equals("ex")){
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
