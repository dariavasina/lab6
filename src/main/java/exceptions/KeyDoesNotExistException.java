package exceptions;

public class KeyDoesNotExistException extends Exception{
    public KeyDoesNotExistException(String key) {
        System.out.println("Key " + key + " does not exist, please try again");
    }
}
