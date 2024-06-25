package hello.decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {
    @Test
    void noDeocoratropattern(){
        Component realComponent=new RealComponent();
        DecoratorPatternClient client=new DecoratorPatternClient(realComponent);
        client.execute();
        client.execute();
        client.execute();
    }
    @Test
    void decotest1(){
        Component realcomponent=new RealComponent();
        MessageDecorator messageDecorator=new MessageDecorator(realcomponent);
        DecoratorPatternClient client=new DecoratorPatternClient(messageDecorator);
        client.execute();
    }
    @Test
    void decotest2(){
        Component realcomponent=new RealComponent();
        MessageDecorator messageDecorator=new MessageDecorator(realcomponent);
        TimeDecorator timeDecorator=new TimeDecorator(messageDecorator);
        DecoratorPatternClient client=new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
