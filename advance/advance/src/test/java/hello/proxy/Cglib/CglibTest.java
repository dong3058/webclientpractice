package hello.proxy.Cglib;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {


    @Test
    void cglib(){
        ConcreateService target=new ConcreateService();

        Enhancer ehancer=new Enhancer();

        ehancer.setSuperclass(ConcreateService.class);
        //어떤 구체클리스인대 set
        ehancer.setCallback(new TimeMethodInterceptor(target));
        //실행할 로직을 set.
        ConcreateService proxy=(ConcreateService) ehancer.create();

        log.info("targetclass={}",target.getClass());
        log.info("proxyclass={}",proxy.getClass());

        proxy.call();
    }
}
