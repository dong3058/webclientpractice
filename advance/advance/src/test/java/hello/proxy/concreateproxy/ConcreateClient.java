package hello.proxy.concreateproxy;

public class ConcreateClient {
    private ConcreateLogic concreateLogic;

    public ConcreateClient(ConcreateLogic concreateLogic) {
        this.concreateLogic = concreateLogic;
    }

    public void execute(){
        concreateLogic.opreation();
    }
}
