package hello.proxy;

import hello.proxy.code.CacheProxy;
import hello.proxy.code.ProxyPatternClient;
import hello.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    void noproxytest(){
        RealSubject subject=new RealSubject();
        ProxyPatternClient client=new ProxyPatternClient(subject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void proxytest(){
        RealSubject realSubject=new RealSubject();
        CacheProxy cacheProxy=new CacheProxy(realSubject);
        ProxyPatternClient client=new ProxyPatternClient(cacheProxy);
        client.execute();
        client.execute();
        client.execute();

    }
}
