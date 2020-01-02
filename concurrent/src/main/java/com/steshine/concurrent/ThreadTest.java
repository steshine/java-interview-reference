package com.steshine.concurrent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by cp123 on 2019/10/15.
 */
public class ThreadTest {

    public static class Data {
        private String packet;

        // True if receiver should wait
        // False if sender should wait
        private boolean transfer = true;

        public synchronized void send(String packet) {
            while (!transfer) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e);
                }
            }
            transfer = false;

            this.packet = packet;
            notifyAll();
        }

        public synchronized String receive() {
            while (transfer) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e);
                }
            }
            transfer = true;

            notifyAll();
            return packet;
        }
    }


    public static class Sender implements Runnable {
        private Data data;

        public Sender(Data data) {
            this.data = data;
        }

        public void run() {
            String packets[] = {
                    "First packet",
                    "Second packet",
                    "Third packet",
                    "Fourth packet",
                    "End"
            };

            for (String packet : packets) {
                data.send(packet);

                // Thread.sleep() to mimic heavy server-side processing
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e);
                }
            }
        }
    }

    public static class Receiver implements Runnable {
        private Data load;

        public Receiver(Data load) {
            this.load = load;
        }

        public void run() {
            for (String receivedMessage = load.receive();
                 !"End".equals(receivedMessage);
                 receivedMessage = load.receive()) {

                System.out.println(receivedMessage);

                // ...
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e);
                }
            }
        }
    }

    class SampleThread extends Thread {
        public int processingCount = 0;

        SampleThread(int processingCount) {
            this.processingCount = processingCount;
            System.out.println("Thread Created");
        }

        @Override
        public void run() {
            System.out.println("Thread " + this.getName() + " started");
            while (processingCount > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread " + this.getName() + " interrupted");
                }
                processingCount--;
            }
            System.out.println("Thread " + this.getName() + " exiting");
        }
    }

    public void givenStartedThread_whenJoinCalled_waitsTillCompletion()
            throws InterruptedException {
        Thread t2 = new SampleThread(1);
        t2.start();
        System.out.println("Invoking join");
        t2.join();
        System.out.println("Returned from join");
        System.out.println(t2.isAlive());
    }



    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        threadTest.givenStartedThread_whenJoinCalled_waitsTillCompletion();
     /*   Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();*/


    }
}
