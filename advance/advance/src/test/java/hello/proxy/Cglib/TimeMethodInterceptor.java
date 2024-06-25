package hello.proxy.Cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;





@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {


        log.info("timeproxy 실행");
        long starttime=System.currentTimeMillis();
        long endtime=System.currentTimeMillis();

        Object result=proxy.invoke(target,args);

        long time=endtime-starttime;

        log.info("time proxy 종료 result time={}",time);

        return result;
    }
}
