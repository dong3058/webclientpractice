package hello.proxy.concreateproxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Timeproxy extends ConcreateLogic {
    private ConcreateLogic concreateLogic;

    public Timeproxy(ConcreateLogic concreateLogic) {
        this.concreateLogic = concreateLogic;
    }

    @Override
    public String opreation() {
        log.info("timeproxy 실행");

        long startime=System.currentTimeMillis();
        String result=concreateLogic.opreation();
        long endtime=System.currentTimeMillis();
        long resultime=endtime-startime;

        log.info("timedecorator 종료 resulttime={}ms",resultime);
        return result;
    }
}
