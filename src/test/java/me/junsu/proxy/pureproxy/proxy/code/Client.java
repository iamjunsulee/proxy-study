package me.junsu.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
    private final Subject subject;

    public Client(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}
