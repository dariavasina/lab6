package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class FilterByShouldBeExpelledCommand extends Command {
    public FilterByShouldBeExpelledCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public FilterByShouldBeExpelledCommand() {
    }

    @Override
    public void execute() {
        //getCollection().filterByShouldBeExpelled(getShouldBeExpelled());
    }
}
