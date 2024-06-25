package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA(){
        Ainterface target=new Aimpl();

        TimeInvcationHandler handler=new TimeInvcationHandler(target);

        Ainterface proxy=(Ainterface) Proxy.newProxyInstance(Ainterface.class.getClassLoader(),new Class[]{Ainterface.class},handler);

        proxy.call();
        log.info("targetclass={}",target.getClass());
        log.info("proxyclass={}",proxy.getClass());

    }

}
