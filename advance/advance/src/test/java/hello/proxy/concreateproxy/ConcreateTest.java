package hello.proxy.concreateproxy;

import org.junit.jupiter.api.Test;

public class ConcreateTest {

    @Test
    void concreatenoproxy(){
        ConcreateLogic concreateLogic=new ConcreateLogic();
        ConcreateClient client=new ConcreateClient(concreateLogic);

        client.execute();;
    }
    @Test
    void addproxy(){
        ConcreateLogic concreateLogic=new ConcreateLogic();

        Timeproxy timeproxy=new Timeproxy(concreateLogic);
        ConcreateClient client=new ConcreateClient(timeproxy);
    }
}
