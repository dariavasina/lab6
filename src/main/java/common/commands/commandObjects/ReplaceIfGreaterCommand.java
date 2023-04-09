package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class ReplaceIfGreaterCommand extends Command {
    public ReplaceIfGreaterCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ReplaceIfGreaterCommand() {
    }

    @Override
    public void execute() {
        //getCollection().replaceIfGreater(getKey(), getValue());
    }
}
