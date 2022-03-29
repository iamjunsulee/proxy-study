package me.junsu.proxy.pureproxy.proxy;

import me.junsu.proxy.pureproxy.proxy.code.CacheProxy;
import me.junsu.proxy.pureproxy.proxy.code.Client;
import me.junsu.proxy.pureproxy.proxy.code.RealSubject;
import me.junsu.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    public void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        Client client = new Client(realSubject);
        client.execute();   //1초
        client.execute();   //1초
        client.execute();   //1초
    }

    @Test
    public void proxyTest() {
        Subject realSubject = new RealSubject();
        CacheProxy proxy = new CacheProxy(realSubject);
        Client client = new Client(proxy);
        client.execute();   //1초
        client.execute();   //0초, 프록시에 데이터가 있으므로 즉시 반환
        client.execute();   //0초
    }
}
