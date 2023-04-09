package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;


public class ClearCommand extends Command {
    public ClearCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ClearCommand() {
    }

    @Override
    public void execute() {
        getCollection().clear();
    }
}
