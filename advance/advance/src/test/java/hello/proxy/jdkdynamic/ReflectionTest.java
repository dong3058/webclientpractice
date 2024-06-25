package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello target=new Hello();

        log.info("start");
        String result1=target.callA();
        log.info("Result={}",result1);


        log.info("start");
        String result2=target.callB();
        log.info("Result={}",result2);
    }

    @Test
    void reflection1() throws Exception{
        Class classHello=Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target=new Hello();
        Method methodCALLA=classHello.getMethod("callA");
        dynamiccall(methodCALLA,target);



        Method methodCALLB=classHello.getMethod("callB");
        dynamiccall(methodCALLA,target);
    }
    private void dynamiccall(Method method,Object target) throws Exception{
        log.info("start");
        Object result=method.invoke(target);
        log.info("result={}",result);


    }
    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }
        public String callB(){
            log.info("callB");
            return "B";
        }

    }
}
