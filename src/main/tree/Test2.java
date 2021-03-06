package main.tree;

public class Test2 {
    static final Object lock = new Object();

    public static class P1 {
        private volatile long b = 0;

        public void set1() {
            synchronized (lock) {
                b = 0;
            }
        }

        public void set2() {
            synchronized (lock) {
                b = -1;
            }
        }

        public void check() {
//            System.out.println(b);
            boolean flag;
            synchronized (lock) {
                flag = 0 != b && -1 != b;
            }
            if (flag) {
                System.out.println(b);
                System.err.println("Error");
            } else {
//                System.out.println("111");
            }
        }
    }

    public static void main(final String[] args) {
        final P1 v = new P1();
        // 线程 1：设置 b = 0
        final Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    v.set1();
                }
            }

            ;
        };
        t1.start();

        // 线程 2：设置 b = -1
        final Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    v.set2();
                }
            }

            ;
        };
        t2.start();

        // 线程 3：检查 0 != b && -1 != b
        final Thread t3 = new Thread() {
            public void run() {
                while (true) {
                    v.check();
                }
            }

            ;
        };
        t3.start();
    }

}
