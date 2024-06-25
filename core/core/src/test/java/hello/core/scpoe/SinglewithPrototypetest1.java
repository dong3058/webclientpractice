package hello.core.scpoe;

import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;


//싱글톤이 내부에 프로토타입을 사용해서 컨테이너에 요청할경우 한개의 프로토타입이 싱글톤에 주입된다.
//그러면 사용자가 싱글톤을 부를떄마다 이미 주입된 프로토타입 1개를 돌려쓰게된다.왜냐면 싱글톤빈은 생성시에한번만 주입하기떄문.
//당연하게도 서로 다른 싱글톤 객체가 프로토타입을 주입받으면 주입받을떄마다 프로토타입이 생성된다.
//아마우리는 이걸 원하느게 아니라 싱글톤을 통해서 프로토타입을 부를떄마다 새로운 프로토 타입을가져오길 바랄것이다.
//웹스코프 프로토타입과다르게 스코프종료까지 스프링에서관리웹에서만작동.
//특히 리퀘스트 스코프의 경우 들어온 요청에대해서 응답이 나갈떄 파기가된다.
//또한 매번 다른 사람이 요청을 하면 서로다른 객체가 만들어져서 이용된다.
public class SinglewithPrototypetest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(Prototypebean.class);

        Prototypebean p1=ac.getBean(Prototypebean.class);
        p1.addcount();
        Assertions.assertThat(p1.getCount()).isEqualTo(1);
        Prototypebean p2=ac.getBean(Prototypebean.class);
        p2.addcount();
        Assertions.assertThat(p2.getCount()).isEqualTo(1);




    }


    @Test
    void singtonclient(){

        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(ClientBean.class,Prototypebean.class);
        ClientBean b1=ac.getBean(ClientBean.class);
        Assertions.assertThat(b1.logic()).isEqualTo(1);
        ClientBean b2=ac.getBean(ClientBean.class);
        Assertions.assertThat(b2.logic()).isEqualTo(1);



    }
    static class ClientBean{

    private final Prototypebean px;


   // @Autowired
    //private Provider<Prototypebean> prototypebeanObjectProvider;//같은기능이라는대 뭔가 오류가난나 나중에알아보자.
    //private  ObjectProvider<Prototypebean> prototypebeanObjectProvider;
        //위의 두개는 프로토타입뿐만아니라 필요한경우에는 쓸수가있다.

  // private  final ObjectProvider<Prototypebean> tt;//애는 생성자 주입방식으로 해본거다.
    @Autowired
    public ClientBean(Prototypebean pp /*ObjectProvider<Prototypebean> t*/){
        this.px=pp;
        //this.tt=t;
    }
    public int logic(){
        //Prototypebean p1=tt.getObject();
        //Prototypebean p1=prototypebeanObjectProvider.get();
        //getobject가 들어오면 그떄 찾아주는대 applicationcontext의 기능을 부분적으로 가져와서쓰는것.
        //직접 application으로 찾는게아닌 대신 찾아주는 대리자 라고생각하면돈다.
        //애는objectfactory를 상속한다.
        px.addcount();
        int x=px.getCount();
        //p1.addcount();
        //int x=p1.getCount();
        return x;
    }


    }

    @Scope("prototype")
    static class Prototypebean{

    private int count=0;

        public int getCount() {
            return count;
        }

        public void addcount() {
            this.count +=1;
        }
        @PostConstruct
        public void init(){
            System.out.println("init"+this);
        }
    }

}
