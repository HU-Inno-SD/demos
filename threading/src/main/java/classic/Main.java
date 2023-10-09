package classic;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        int nrOfThreads = 10;
        int nrOfCountsPerThread = 100;

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < nrOfThreads; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < nrOfCountsPerThread; j++) {
                    c.increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t.start();
            threads.add(t);
        }


        for (Thread t : threads) {
            t.join();
        }

        System.out.println(c.getCount());
    }
}
