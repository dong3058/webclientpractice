package hello.proxy.code;

public class ProxyPatternClient {
    private Subject subect;

    public ProxyPatternClient(Subject subect) {
        this.subect = subect;
    }

    public void execute(){

        subect.operation();
    }

}
