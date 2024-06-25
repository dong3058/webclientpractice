package hello.core.Common;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value="request")
//http요청이 들어오면 그오쳥덩 1개씩 생성이되고 요청이끝나면 소멸된다.
public class MyLogger {
    private String uuid;
    private String requesturl;

    public void seturl(String requesturl){
        this.requesturl=requesturl;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requesturl+"]"+message);
    }

    @PostConstruct
    public void init(){
        uuid= UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean crate" +this);


    }
    @PreDestroy
    public void close(){

        System.out.println("["+uuid+"] request scope bean close" +this);


    }


}
