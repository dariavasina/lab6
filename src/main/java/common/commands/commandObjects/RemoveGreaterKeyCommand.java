package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class RemoveGreaterKeyCommand extends Command {
    public RemoveGreaterKeyCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public RemoveGreaterKeyCommand() {
    }

    @Override
    public void execute() {
        //getCollection().removeGreaterKey(getKey());
    }
}
