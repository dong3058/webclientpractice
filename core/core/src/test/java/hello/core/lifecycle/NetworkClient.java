package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;//스프링 종속이아닌 자바의 표준 인터페이스. 다른대서도 동작한다.컴포넌트
//스캔과 잘어울린다.단 외부라이브러이 적용이안되므로 외부라이브러리는 bean을 이용하도록하자꾸나.
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
//초기화 알려주는 이니셜라이징 빈. disconnect을 호출하는 disposablebean 단점이라면 초순수 스프링 즉 자바코드로 테스트불가.
//지금은 거의 사용치않는다.


public class NetworkClient  {
    private String url;

   /* @Override
    public void destroy() throws Exception {
        disconnect();
    }*/

    /*@Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }*/
    @PostConstruct//셍상이후
    public void init(){
        connect();
        call("초기화 연결 메세지");

    }
    @PreDestroy//이름그대로 파괴하기전
    public void ends(){
    disconnect();
    }

    public NetworkClient(){
        System.out.println("생성자 호출:"+url);

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect:"+url);
    }
    public void call(String message){
        System.out.println("call:"+url+"message:"+message);
    }
    public void disconnect(){
        System.out.println("end");

    }
}
