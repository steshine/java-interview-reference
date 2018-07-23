package com.steshine;

/**
 * Created by cp123 on 2017/6/20.
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        NoVisibility.number = number;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (ready) {
                sleeps();
                System.out.println(Thread.currentThread().getName() + ": read " + getNumber());
            }
        }
    }

    private static class WriteThread extends Thread {
        @Override
        public void run() {
            while (ready) {
                sleeps();
                setNumber(number + 1);
                System.out.println(Thread.currentThread().getName() + ": write " + getNumber());
            }
        }
    }

    private static void sleeps() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new ReaderThread().start();
        new WriteThread().start();
        number = 42;
        ready = true;
    }
}
