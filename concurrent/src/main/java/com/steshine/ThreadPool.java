package com.steshine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by skychen on 2019/4/3.
 */
public class ThreadPool {
    private static ExecutorService executor = Executors.newFixedThreadPool(20);
    public static void main(String[] args) {
        for(int i = 0; i< 10;i++){
            executor.execute(()->{
                System.out.println("halo " + Thread.currentThread().getId());
            });
        }
    }

}
