package commands;

import collection.StudyGroupCollectionManager;

public class ReplaceIfGreaterCommand extends Command{
    public ReplaceIfGreaterCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().replaceIfGreater(getKey(), getValue());
    }
}
