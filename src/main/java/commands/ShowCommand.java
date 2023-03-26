package commands;

import collection.StudyGroupCollectionManager;

public class ShowCommand extends Command{
    public ShowCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().show();
    }
}
