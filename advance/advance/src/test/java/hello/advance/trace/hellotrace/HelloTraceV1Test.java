package hello.advance.trace.hellotrace;

import hello.advance.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {

    @Test
    void begin_end(){
        HelloTraceV1 trace=new HelloTraceV1();
        TraceStatus status=trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_ex(){
        HelloTraceV1 trace=new HelloTraceV1();
        TraceStatus status=trace.begin("hello");
        trace.exception(status,new IllegalStateException());

    }

}