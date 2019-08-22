package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1.判断
            while (number != 0) {
                // 等待不能生产
                condition.await();
            }
            // 2.干活
            System.out.print(Thread.currentThread().getName() + "\t" + number);
            number++;
            System.out.println("\t>\t" + number);
            // 3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1.判断
            while (number == 0) {
                // 等待不能生产
                condition.await();
            }
            // 2.干活
            System.out.print(Thread.currentThread().getName() + "\t" + number);
            number--;
            System.out.println("\t>\t" + number);
            // 3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {

                } finally {

                }
            }
        }, "生产者").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {

                } finally {

                }
            }
        }, "消费者").start();
    }
}
