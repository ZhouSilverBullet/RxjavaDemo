package com.zhousatio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NewThreadScheduler implements Scheduler {
    public ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "new Thread");
        }
    });

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
