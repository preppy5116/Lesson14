package ExecutionManagerPackage;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutionManagerImpl implements ExecutionManager {
    AtomicInteger completedTaskCount = new AtomicInteger(0);
    AtomicInteger failedTaskCount = new AtomicInteger(0);
    AtomicInteger interruptedTaskCount = new AtomicInteger(0);
    AtomicBoolean isInterrupted = new AtomicBoolean(false);
    AtomicInteger allTaskCount = new AtomicInteger();
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            Arrays.stream(tasks).map(task ->
                    {
                        Runnable runnable = () -> {
                            if(isInterrupted.get())
                                interruptedTaskCount.getAndIncrement();
                            else
                                try {
                                    task.run();
                                    completedTaskCount.getAndIncrement();
                                } catch (Exception e) {failedTaskCount.getAndIncrement();}
                            if(allTaskCount.incrementAndGet() == tasks.length) {
                                callback.run();
                                executorService.shutdown();
                            }
                        };
                        return runnable;
                    }
            ).forEach(executorService::submit);



            return new Context() {
                @Override
                public int getCompletedTaskCount() {
                    return completedTaskCount.get();
                }

                @Override
                public int getFailedTaskCount() {
                    return failedTaskCount.get();
                }

                @Override
                public int getInterruptedTaskCount() {
                    return interruptedTaskCount.get();
                }

                @Override
                public void interrupt() {
                    isInterrupted.set(true);
                }

                @Override
                public boolean isFinished() {
                    return executorService.isShutdown();
                }
            };
    }
}
