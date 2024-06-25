package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvcationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvcationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("timeproxy 실행");
        long starttime=System.currentTimeMillis();


        Object result=method.invoke(target,args);
        long endtime=System.currentTimeMillis();

        long time=endtime-starttime;

        log.info("time proxy 종료 result time={}",time);

        return result;
    }
}
