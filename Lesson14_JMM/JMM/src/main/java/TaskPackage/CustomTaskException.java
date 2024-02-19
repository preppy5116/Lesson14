package TaskPackage;

public class CustomTaskException extends RuntimeException {
    public CustomTaskException(String message) {
        super(message);
    }

    public CustomTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomTaskException(Throwable cause) {
        super(cause);
    }

    public CustomTaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}