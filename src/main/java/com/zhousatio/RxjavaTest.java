package com.zhousatio;

public class RxjavaTest {
    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<String> t) {
                System.out.println("" + Thread.currentThread());
                t.onNext("string");
                t.onCompleted();
            }
        })
//                .subscribeOn(Schedulers.IO)
                .observerOn(Schedulers.NEW_THREAD)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s + " " + Thread.currentThread());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted " + Thread.currentThread());
                    }
                });
//        new Thread("BBB") {
//            @Override
//            public void run() {
//                new Thread("AAA") {
//                    @Override
//                    public void run() {
//                        System.out.println("" + Thread.currentThread());
//                    }
//                }.start();
//            }
//        }.start();

        new Thread("BBB") {
            @Override
            public void run() {
                System.out.println("" + Thread.currentThread());

                new Thread("AAA") {
                    @Override
                    public void run() {
                        System.out.println("" + Thread.currentThread());
                    }
                }.start();
            }
        }.start();

        while (true) {

        }


    }
}
