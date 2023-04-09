package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class CountByStudentsCountCommand extends Command {
    public CountByStudentsCountCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public CountByStudentsCountCommand() {
    }

    @Override
    public void execute() {
        //getCollection().countByStudentsCount(getStudentsCount());
    }
}
