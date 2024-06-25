package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycle {
    //스프링 빈의 라이프사이클 컨테이너생성->빈 생성(생성자주입이면 같이일어남의존관계주입이)->의존관꼐주입->초기화콜백
    //>사용>소멸전콜백>스프링종료 초기화콜백:의존관계까지 주입이 되고나면이제 초기화가 가능하다고 알리는것.
    //객체의 생성보단 초기화단계가 더무거우 ㄴ대 왜나면 초기화는 다른 연결이라던가 무거운 역할도 하기때문.
    //단 일반적인 변수 생성같은거는 생성자 주입으로 한번에 만들어도 상관은없다.
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac=new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client=ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{
    @Bean//(initMethod = "init",destroyMethod ="ends")//네트워크 클라이언트 내부의 메서드를 이용해서 초기화,종료하기.
    //장점:스프링에대한 의존성이없다. 외부라이브러리(코드 수정이 불가한)에도 초기화,종료 메서드를 호출가능.
    //빈으로ㅑ 등록한 destorymethod는 특별한기능이잇는대 infer기느잉다. 즉 destory의 defaul는 "(infer)"이다.
    //대부분의 라이브러리는 clsoe,shutdown이라는 이름의 종료 메서드를 이용하는대 ㅇ런 이름이 달린애를
    //자동으로 호출한다.그러므로 직접 bean으로 등록하면 굳이 destory메서드를 등록하지않아도된다.
    public NetworkClient network(){
        NetworkClient networkclient=new NetworkClient();
        networkclient.setUrl("zxcxc");
        return networkclient;
    }

    }
}
