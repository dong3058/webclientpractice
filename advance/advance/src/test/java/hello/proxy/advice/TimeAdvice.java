package hello.proxy.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("timeproxy 실행");
        long starttime=System.currentTimeMillis();
        long endtime=System.currentTimeMillis();

        //Object result=proxy.invoke(target,args);
        Object result=invocation.proceed();
        //타겟찾아서 arg넣어주고 로직까지 실행해줌.
        //타겟클래스 에대한 정보는 invocation안에들어있다.

        long time=endtime-starttime;

        log.info("time proxy 종료 result time={}",time);

        return result;
    }
}
