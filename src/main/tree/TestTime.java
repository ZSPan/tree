package main.tree;

public class TestTime {

    public volatile long startTime = System.currentTimeMillis();


    public static void main(String[] args) {
        TestTime testTime = new TestTime();
        Thread thread = new Thread(() -> {
            long currentTime;
            while (true) {
                currentTime = System.currentTimeMillis();
                if (currentTime != testTime.startTime) {
                    testTime.startTime = currentTime;
                }
            }
        });
        thread.start();
        long count;
        long currentTime;
        while (true) {
            count = 0;
            currentTime = System.currentTimeMillis();
            while (currentTime == testTime.startTime) {
                count++;
            }
            System.out.println(count);
        }
    }

}
