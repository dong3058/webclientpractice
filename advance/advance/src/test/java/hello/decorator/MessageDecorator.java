package hello.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class MessageDecorator implements Component{
    private  Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    public String operation(){
        log.info("messagedecorator 실행");
        String operation =component.operation();
        String decoresult="***"+operation+"***";
        return decoresult;
    }


}
