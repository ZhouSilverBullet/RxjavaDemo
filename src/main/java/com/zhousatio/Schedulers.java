package com.zhousatio;

public class Schedulers {
    public static final Scheduler IO = new SchedulerIO();
    public static final Scheduler NEW_THREAD = new NewThreadScheduler();
}
