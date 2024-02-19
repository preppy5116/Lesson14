package TaskPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Task<String> task = new Task<>(() -> "My task");
        addExecutorService(task);
    }

    public static void addExecutorService(Task task) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> task.get());
        executorService.submit(() -> task.get());
        executorService.submit(() -> task.get());
        executorService.submit(() -> task.get());
        executorService.shutdown();
    }
}

