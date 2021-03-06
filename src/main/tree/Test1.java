package main.tree;

import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    static final long COUNT = 1000;
    static final int threadNum = 640;
    AtomicInteger sum = new AtomicInteger(0);
    AtomicInteger delta = new AtomicInteger(0);

    static class Cal extends Thread {

        final Test1 test1;

        Cal(Test1 test1) {
            this.test1 = test1;
        }

        @Override
        public void run() {
            while (true) {
                int n = test1.delta.incrementAndGet();
                if (n > COUNT) {
                    test1.delta.decrementAndGet();
                    return;
                } else {
                    test1.sum.addAndGet(n);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    static class A {
        int sum = 0;
        int delta = 0;
    }

    static class B extends Thread {
        private final A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (a) {
                    a.delta++;
                    if (a.delta > COUNT) {
                        a.delta--;
                        return;
                    } else {
                        a.sum += a.delta;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }

    public static void test1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Test1 test1 = new Test1();
        Cal[] cals = new Cal[threadNum];
        for (int i = 0; i < cals.length; i++) {
            Cal cal = new Cal(test1);
            cals[i] = cal;
            cal.start();
        }
        for (Cal cal : cals) {
            cal.join();
        }
        System.out.println("test1:" + (System.currentTimeMillis() - startTime));
    }

    public static void test2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        A a = new A();
        B[] bs = new B[threadNum];
        for (int i = 0; i < bs.length; i++) {
            B b = new B(a);
            bs[i] = b;
            b.start();
        }
        for (B b : bs) {
            b.join();
        }
        System.out.println("test2:" + (System.currentTimeMillis() - startTime));
    }
}
