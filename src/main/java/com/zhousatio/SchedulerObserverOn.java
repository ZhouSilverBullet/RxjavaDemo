package com.zhousatio;

import java.util.LinkedList;
import java.util.Queue;

public class SchedulerObserverOn<T> implements Observable.OnSubscribe<T> {
    private Observable.OnSubscribe<T> onSubscribe;
    private Scheduler scheduler;

    public SchedulerObserverOn(Observable.OnSubscribe<T> onSubscribe, Scheduler scheduler) {
        this.onSubscribe = onSubscribe;
        this.scheduler = scheduler;
    }

    @Override
    public void call(Subscriber<T> t) {
        ObserverOnSubscribe<T> subscribe = new ObserverOnSubscribe<>(scheduler, t);
        onSubscribe.call(subscribe);
    }

    static final class ObserverOnSubscribe<T> extends Subscriber<T> implements Runnable {
        private Scheduler scheduler;
        private Subscriber<T> child;
        private Queue<T> queue;

        public ObserverOnSubscribe(Scheduler scheduler, Subscriber<T> child) {
            this.scheduler = scheduler;
            this.child = child;

            queue = new LinkedList<>();
        }

        @Override
        public void run() {
            if (queue.isEmpty()) {
                child.onCompleted();
                return;
            }
            T poll = queue.poll();
            child.onNext(poll);
        }

        @Override
        void onError(Throwable t) {
            schedule();
        }

        @Override
        void onNext(T t) {
            queue.offer(t);
            schedule();
        }

        @Override
        void onCompleted() {
            schedule();
        }

        public void schedule() {
            scheduler.execute(this);
        }
    }
}
