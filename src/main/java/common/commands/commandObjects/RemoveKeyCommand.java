package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class RemoveKeyCommand extends Command {
    public RemoveKeyCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public RemoveKeyCommand() {
    }

    @Override
    public void execute() {
        //getCollection().removeByKey(getKey());
    }
}
