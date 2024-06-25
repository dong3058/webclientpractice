package hello.advance.trace.logtrace;

import hello.advance.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status,Exception e);
}
