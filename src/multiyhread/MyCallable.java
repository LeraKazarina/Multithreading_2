package multithread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    final private String name;
    final private int sleepingTime;
    final private int STEP_COUNT = 40;
    private int msgCount;

    public MyCallable(String name, int sleepingTime) {
        this.name = name;
        this.sleepingTime = sleepingTime;
        this.msgCount = 0;
    }

    @Override
    public String call() throws Exception {
        try {
            for (int i = 0; i < STEP_COUNT; i++) {
                System.out.printf("%s. Всем привет!\n", name);
                msgCount++;
                Thread.sleep(sleepingTime);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s отправил %d сообщений\n", name, this.msgCount);
        }
        System.out.printf("%s завершен\n", name);
        return String.format("%s отправил %d сообщений", name, this.msgCount);
    }
}
