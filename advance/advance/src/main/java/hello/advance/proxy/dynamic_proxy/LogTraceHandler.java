package hello.advance.proxy.dynamic_proxy;

import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        TraceStatus status=null;
        try{
            String msg= method.getDeclaringClass().getSimpleName()+"."+method.getName()+"()";
            status=logTrace.begin(msg);
            Object result=method.invoke(target,args);
            logTrace.end(status);

            return result;

        }
        catch(Exception e){
            logTrace.exception(status,e);
            throw e;
        }




    }
}
