package commands;

import collection.StudyGroupCollectionManager;


public class ClearCommand extends Command{
    public ClearCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().clear();
    }
}
