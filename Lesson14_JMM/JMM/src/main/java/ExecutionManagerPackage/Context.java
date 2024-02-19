package ExecutionManagerPackage;

/**
 * Метод getCompletedTaskCount() возвращает количество task, которые на текущий момент успешно выполнились.
 * Метод getFailedTaskCount() возвращает количество task, при выполнении которых произошел Exception.
 * Метод interrupt() отменяет выполнения task, которые еще не начали выполняться.
 * Метод getInterruptedTaskCount() возвращает количество task, которые не были выполнены из-за отмены
 * (вызовом предыдущего метода).
 * Метод isFinished() вернет true, если все таски были выполнены или отменены, false в противном случае.
 */
public interface Context {


    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();

}
