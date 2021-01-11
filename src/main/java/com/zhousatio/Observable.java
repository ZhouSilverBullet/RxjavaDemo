package com.zhousatio;

public class Observable<T> {
    final OnSubscribe<T> onSubscribe;

    public Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> subscribe) {
        return new Observable<>(subscribe);
    }

    public Subscription subscribe(Subscriber<T> subscriber) {
        if (onSubscribe != null) {
            onSubscribe.call(subscriber);
        }
        return subscriber;
    }

    public interface OnSubscribe<T> {
        void call(Subscriber<T> t);
    }

    public Observable<T> subscribeOn(Scheduler scheduler) {
        return create(new SchedulerOnSubscribe<>(this, scheduler));
    }

    public Observable<T> observerOn(Scheduler scheduler) {
        return create(new SchedulerObserverOn<>(onSubscribe, scheduler));
    }
}
