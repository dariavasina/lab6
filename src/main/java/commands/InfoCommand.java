package commands;

import collection.StudyGroupCollectionManager;

public class InfoCommand extends Command{
    public InfoCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().info();
    }
}
