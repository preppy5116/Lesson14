package ExecutionManagerPackage;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {e.printStackTrace();}
        };
        Runnable[] tasks =  new Runnable[10];
        Arrays.fill(tasks, runnable);
        ExecutionManager executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(() -> System.out.println("Done!"), tasks);
        context.interrupt();
    }
}
