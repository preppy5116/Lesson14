package ExecutionManagerPackage;
/**
 * Метод execute – это неблокирующий метод, который сразу возвращает объект Context.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
