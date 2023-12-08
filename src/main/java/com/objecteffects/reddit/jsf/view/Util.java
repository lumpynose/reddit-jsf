package com.objecteffects.reddit.jsf.view;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedThreadFactory;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Util {
    @Resource
    private ManagedThreadFactory threadFactory;

    private final ExecutorService tpe =
            new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS,
                    new ArrayBlockingQueue<>(1), this.threadFactory);

    /**
     * @return
     */
    public ExecutorService getExecutorService() {
        return this.tpe;
    }
}
