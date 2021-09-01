import multithread.MyCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int THREADS_SLEEPING_TIME = 3000;
        int MAIN_THREAD_SLEEPING_TIME = 15000;
        int THREAD_COUNT = 4;

        final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<String>> futures = new ArrayList<>();
        List<MyCallable> myCallables = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            myCallables.add(
                    new MyCallable(String.format("Я поток %d", i + 1),
                            THREADS_SLEEPING_TIME)
            );
        }
        futures = threadPool.invokeAll(myCallables,MAIN_THREAD_SLEEPING_TIME, TimeUnit.MILLISECONDS);
        for (Future<String> future : futures) {
            if(!future.isCancelled())
                System.out.println(future.get());
        }
        threadPool.shutdown();
    }
}
