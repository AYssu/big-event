package com.bigevent;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testPool()
    {
        ThreadLocal threadLocal = new ThreadLocal();
        new Thread(()->{
            threadLocal.set("阿夜");
            System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
        },"blue").start();
        new Thread(()->{
            threadLocal.set("阿丽");
            System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+": "+threadLocal.get());
        },"green").start();
    }
}
