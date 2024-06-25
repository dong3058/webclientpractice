package hello.advance.trace.logtrace;

import hello.advance.trace.TraceId;
import hello.advance.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {
    FieldLogTrace trace=new FieldLogTrace();
    @Test
    void logtracetest(){
        TraceStatus status= trace.begin("hellow1");
        TraceStatus status2=trace.begin("hello2");
        trace.end(status2);
        trace.end(status);
    }

}