package main.tree;

public class A {

    static boolean flag = false;
    static int COUNT = 10;

    static class Num extends Thread {
        int a = 1;
        int count = 0;

        @Override
        public void run() {
            while (count < COUNT) {
                count++;
                while (true) {
                    if (flag) {
                        System.out.println(a++);
                        flag = !flag;
                        break;
                    }
                }
            }
        }
    }

    static class Char extends Thread {
        char c = 'A';
        int count = 0;

        @Override
        public void run() {
            while (count < COUNT) {
                count++;
                while (true) {

                    if (!flag) {
                        System.out.println(c++);
                        flag = !flag;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Num num = new Num();
        Char cha = new Char();
        num.start();
        cha.start();
        num.join();
        cha.join();
        System.out.println("end!");
    }

}
