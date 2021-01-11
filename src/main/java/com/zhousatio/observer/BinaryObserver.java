package com.zhousatio.observer;

public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println(getClass() + " " + subject.getState());
    }
}
