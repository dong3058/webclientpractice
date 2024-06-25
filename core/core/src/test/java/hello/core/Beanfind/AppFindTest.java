package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AppFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);



    @Test
    void findAllBean(){

        String[] beanDefinitionNames=ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            Object bean=ac.getBean(beanDefinitionName);
            System.out.println("name="+beanDefinitionName+"object="+bean);

        }
    }



    @Test
    void findAll(){


        Map<String, Object> beansOfType=ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key=" + key + "value=" + beansOfType.get(key));
        }
        System.out.println("beansoftype:"+beansOfType);
    }

    @Test
    void findAllBeans(){

        String[] beanDefinitionNames=ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            BeanDefinition beanDefinition=ac.getBeanDefinition(beanDefinitionName);//빈 디피니션==>메타 데이터를 가져오는것.
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){

            //내가등록한애들을 의미함. role.appliction
                Object bean=ac.getBean(beanDefinitionName);
                System.out.println("name="+beanDefinitionName+"object="+bean);

            }

        }
    }

}
