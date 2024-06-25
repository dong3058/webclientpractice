 package hello.advance.trace.hellotrace.v3;


import hello.advance.trace.TraceId;
import hello.advance.trace.TraceStatus;
import hello.advance.trace.hellotrace.HelloTraceV2;
import hello.advance.trace.hellotrace.v2.OrderRepositoryv2;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

 @Service
 @RequiredArgsConstructor
 public class OrderServicev3 {


     private final OrderRepositoryv3 orderRepository;
     private final LogTrace trace;
     public void orderitem(String itemid){

         TraceStatus status= trace.begin("Orderservice.orderitem()");
         try {
             orderRepository.save(itemid);
             trace.end(status);
         }
         catch(Exception e){
             trace.exception(status,e);
             throw e;
         }

     }


 }
