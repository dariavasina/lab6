package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class InfoCommand extends Command {
    public InfoCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public InfoCommand() {
    }

    @Override
    public void execute() {
        getCollection().info();
    }
}
