package hello.advance.trace.hellotrace.v3;


import hello.advance.trace.TraceId;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV2;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryv3 {

    private final LogTrace trace;
    public void save(String itemid){
        TraceStatus status= trace.begin("Orderrepository.save()");
        try {
            if(itemid.equals("ex")){
                throw new IllegalStateException("예외발생!");
            }
            sleep(1000);
            trace.end(status);
        }
        catch(Exception e){
            trace.exception(status,e);
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
