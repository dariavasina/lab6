package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class ReplaceIfLowerCommand extends Command {
    public ReplaceIfLowerCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ReplaceIfLowerCommand() {
    }

    @Override
    public void execute() {
        //getCollection().replaceIfLower(getKey(), getValue());
    }
}
