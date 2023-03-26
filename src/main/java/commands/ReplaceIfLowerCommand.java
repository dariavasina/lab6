package commands;

import collection.StudyGroupCollectionManager;

public class ReplaceIfLowerCommand extends Command{
    public ReplaceIfLowerCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().replaceIfLower(getKey(), getValue());
    }
}
