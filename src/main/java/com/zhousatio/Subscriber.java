package com.zhousatio;

public abstract class Subscriber<T> implements Subscription {

    abstract void onError(Throwable t);

    abstract void onNext(T t);

    abstract void onCompleted();

    @Override
    public void unSubscribe() {

    }

    @Override
    public boolean isUnsubscribe() {
        return false;
    }
}
