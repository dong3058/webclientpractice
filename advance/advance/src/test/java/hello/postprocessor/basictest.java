package hello.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class basictest {

    @Test
    void basicconfig(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);

        //A a=ac.getBean("beana",A.class);
        //a.helloA();
        B b=ac.getBean("beana",B.class);
        b.helloB();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,()->ac.getBean(A.class));
        //ac에서 a클래스가 존재시 오류가 터지는대
    }

    @Slf4j
    @Configuration
    static class Config{
        @Bean(name="beana")
        public A a(){
            return new A();
        }
        @Bean//빈으로 등록한하면 알아서 등록하고 처리함.
        public Beanchange beanchange(){

            return new Beanchange();
        }
    }

    @Slf4j
    static class A{
        public void helloA(){
            log.info("helloA");
        }
    }
    @Slf4j
    static class B{
        public void helloB(){
            log.info("helloB");
        }
    }

    @Slf4j
    static class Beanchange implements BeanPostProcessor{
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("bean_name:{}",beanName);
            log.info("beantype:{}",bean.getClass());

            if(bean instanceof A){
                return new B();
            }
            return bean;
        }
    }
}
