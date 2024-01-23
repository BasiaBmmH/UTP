package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {
    private String letters;
    private List<Thread> threads;

    public Letters(String letters) {
        this.letters = letters;
        this.threads = new ArrayList<>();

        for (char c : letters.toCharArray()) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print(c);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Thread " + c);
            threads.add(thread);
        }
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
