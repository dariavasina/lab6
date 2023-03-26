package exceptions;

public class CommandDoesNotExistException extends Exception{
    public CommandDoesNotExistException(String command) {
        System.out.printf("Command %s does not exist, please enter a valid command name\n", command);
    }
}
