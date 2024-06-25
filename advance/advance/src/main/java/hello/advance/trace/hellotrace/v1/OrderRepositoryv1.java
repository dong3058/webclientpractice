package hello.advance.trace.hellotrace.v1;


import hello.advance.trace.TraceStatus;


import hello.advance.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryv1 {

    private final HelloTraceV1 helloTracev1;
    public void save(String itemid){
        TraceStatus status= helloTracev1.begin("Orderrepository.save()");
        try {
            if(itemid.equals("ex")){
                throw new IllegalStateException("예외발생!");
            }
            sleep(1000);
            helloTracev1.end(status);
        }
        catch(Exception e){
            helloTracev1.exception(status,e);
            throw e;
        }




    }


    public void sleep(int millis){
        try{
        Thread.sleep(millis);}
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
