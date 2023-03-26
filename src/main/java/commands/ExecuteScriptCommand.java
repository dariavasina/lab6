package commands;

import collection.StudyGroupCollectionManager;
import exceptions.CommandExecutionException;
import exceptions.FileAccessException;

public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        try {
            getCollection().executeCommandsFromFile(getFileName());
        } catch (FileAccessException e) {
            System.out.println(e.getMessage());
        } catch (CommandExecutionException e) {
            System.out.println(e.getMessage());
        }

    }
}
