package me.junsu.proxy.pureproxy.decorator;

import me.junsu.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {
    @Test
    public void noDecoratorTest() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(realComponent);
        decoratorPatternClient.execute();
    }

    @Test
    public void decoratorTest1() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(messageDecorator);
        decoratorPatternClient.execute();
    }

    @Test
    public void decoratorTest2() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);   //부가기능
        Component timeDecorator = new TimeDecorator(messageDecorator);      //부가기능
        //client 는 전혀 수정하지 않고, 프록시를 추가하면서 부가기능을 추가할 수 있다.
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(timeDecorator);
        decoratorPatternClient.execute();
    }
}
