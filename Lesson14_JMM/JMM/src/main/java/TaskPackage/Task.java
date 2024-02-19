package TaskPackage;

import java.util.concurrent.Callable;

/**
 * Данный класс в конструкторе принимает экземпляр java.util.concurrent.Callable.
 * Callable похож на Runnuble, но результатом его работы является объект (а не void).
 *
 * Метод get() который возвращает результат работы Callable.
 * Выполнение callable должен начинать тот поток, который первый вызвал метод get().
 * Если несколько потоков одновременно вызывают этот метод, то выполнение должно начаться только
 * в одном потоке, а остальные должны ожидать конца выполнения (не нагружая процессор).
 * Если при вызове get() результат уже просчитан, то он должен вернуться сразу,
 * (даже без задержек на вход в синхронизированную область).
 * Если при просчете результата произошел Exception, то всем потокам при вызове get(),
 * надо кидать этот Exception, обернутый в ваш RuntimeException (подходящее название своему
 * ексепшену придумайте сами).етод get() который возвращает результат работы Callable.
 * Выполнение callable должен начинать тот поток, который первый вызвал метод get(). Если несколько потоков одновременно вызывают этот метод, то выполнение должно начаться только в одном потоке, а остальные должны ожидать конца выполнения (не нагружая процессор).
 * Если при вызове get() результат уже просчитан, то он должен вернуться сразу,
 * (даже без задержек на вход в синхронизированную область).
 * Если при просчете результата произошел Exception, то всем потокам при вызове get(),
 * надо кидать этот Exception, обернутый в ваш RuntimeException (подходящее название своему ексепшену придумайте сами).
 * @param <T>
 */
public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result;
    private volatile CustomTaskException exception;
    private volatile boolean exceptionFlag = false;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (exception != null) {
            throw exception;
        }
        if (result != null) {
            System.out.println("Get result from cache");
            return result;
        }
        synchronized (this) {
            if (result == null)
                try {
                    System.out.println("New result");
                    result = callable.call();
                } catch (Exception e) {
                    exception = new CustomTaskException(e);
                    throw exception;
                }
        }
        return result;
    }

}