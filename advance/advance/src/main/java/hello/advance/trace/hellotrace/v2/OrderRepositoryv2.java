package hello.advance.trace.hellotrace.v2;


import hello.advance.trace.TraceId;
import hello.advance.trace.TraceStatus;

import hello.advance.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryv2 {

    private final HelloTraceV2 helloTracev2;
    public void save(TraceId traceId,String itemid){
        TraceStatus status= helloTracev2.begin("Orderrepository.save()");
        try {
            if(itemid.equals("ex")){
                throw new IllegalStateException("예외발생!");
            }
            sleep(1000);
            helloTracev2.end(status);
        }
        catch(Exception e){
            helloTracev2.exception(status,e);
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
