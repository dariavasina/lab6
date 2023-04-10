package common.exceptions;

public class EmptyCollectionException extends Throwable {
    public EmptyCollectionException() {
        super("The collection is empty");
    }
}
