package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;
import common.exceptions.CommandExecutionException;
import common.exceptions.FileAccessException;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ExecuteScriptCommand() {
    }

    @Override
    public void execute() {
        /*try {
            getCollection().executeCommandsFromFile(getFileName());
        } catch (FileAccessException e) {
            System.out.println(e.getMessage());
        } catch (CommandExecutionException e) {
            System.out.println(e.getMessage());
        }*/

    }
}
