package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class ShowCommand extends Command {
    public ShowCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ShowCommand() {
    }

    @Override
    public void execute() {
        getCollection().show();
    }
}
