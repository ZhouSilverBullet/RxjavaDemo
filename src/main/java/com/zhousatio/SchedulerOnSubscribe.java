package com.zhousatio;

public class SchedulerOnSubscribe<T> implements Observable.OnSubscribe<T> {
    private Observable<T> source;
    private Scheduler scheduler;

    public SchedulerOnSubscribe(Observable<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    public void call(Subscriber<T> sub) {
        scheduler.execute(new Runnable() {
            @Override
            public void run() {
                Subscriber<T> subscriber = new Subscriber<T>() {
                    @Override
                    void onError(Throwable t) {
                        sub.onError(t);
                    }

                    @Override
                    void onNext(T t) {
                        sub.onNext(t);
                    }

                    @Override
                    void onCompleted() {
                        sub.onCompleted();
                    }
                };
                source.subscribe(subscriber);
            }
        });
    }
}
