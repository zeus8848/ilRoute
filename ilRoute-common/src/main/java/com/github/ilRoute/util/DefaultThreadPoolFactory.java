package com.github.ilRoute.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lwx
 */
public class DefaultThreadPoolFactory {
    public static ExecutorService getExecutorService(String name) {
        return new ThreadPoolExecutor(
                2, 4, 0, TimeUnit.MILLISECONDS
                ,new ArrayBlockingQueue<Runnable>(1), r -> {
                    Thread thread = new Thread(r, name);
                    thread.setDaemon(true);
                    return thread; }
                ,new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
