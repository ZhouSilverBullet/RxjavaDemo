package com.zhousatio.observer;

public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println(getClass() + " " + subject.getState());
    }
}
