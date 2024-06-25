package hello.advance.trace.hellotrace;

import hello.advance.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end(){
        HelloTraceV2 trace=new HelloTraceV2();
        TraceStatus status=trace.begin("hello");
        TraceStatus status2=trace.beginsync(status.getTraceId(),"hello2");
        trace.end(status2);
        trace.end(status);
    }

    @Test
    void begin_ex(){
        HelloTraceV2 trace=new HelloTraceV2();
        TraceStatus status=trace.begin("hello");
        trace.exception(status,new IllegalStateException());

    }

}