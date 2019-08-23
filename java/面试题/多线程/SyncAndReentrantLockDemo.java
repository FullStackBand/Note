package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResoure {
    private int number = 1;//A=1;B=2;C=3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            // 1.判断
            while (number != 1) {
                c1.await();
            }
            // 2.干活
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3.通知
            number = 2;//修改标志位
            c2.signal();//通知B可以运行
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 1.判断
            while (number != 2) {
                c2.await();
            }
            // 2.干活
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3.通知
            number = 3;//修改标志位
            c3.signal();//通知B可以运行
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            // 1.判断
            while (number != 3) {
                c3.await();
            }
            // 2.干活
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            System.out.println();
            // 3.通知
            number = 1;//修改标志位
            c1.signal();//通知B可以运行
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResoure shareResoure = new ShareResoure();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                shareResoure.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                shareResoure.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                shareResoure.printC();
            }
        }, "C").start();
    }
}
