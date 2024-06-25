package hello.advance.proxy.factory;

import hello.advance.trace.TraceStatus;
import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.Interceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {
    private  final LogTrace logTrace;
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status=null;

        try{
            Method method=invocation.getMethod();
            String msg=method.getDeclaringClass().getSimpleName()+"."+method.getName()+"()";
            status=logTrace.begin(msg);
            Object result=invocation.proceed();
            logTrace.end(status);
            return result;

        }
        catch(Exception e){
            logTrace.exception(status,e);
            throw e;
        }

    }
}
