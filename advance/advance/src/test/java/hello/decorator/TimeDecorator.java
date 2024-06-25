package hello.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements  Component{
    private  Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("타임 데코레이터 실행");
        long startime=System.currentTimeMillis();
        String result=component.operation();
        long endtime=System.currentTimeMillis();
        long resultime=endtime-startime;

        log.info("timedecorator 종료 resulttime={}ms",resultime);
        return result;
    }
}
