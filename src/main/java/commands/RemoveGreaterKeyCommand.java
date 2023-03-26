package commands;

import collection.StudyGroupCollectionManager;

public class RemoveGreaterKeyCommand extends Command{
    public RemoveGreaterKeyCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().removeGreaterKey(getKey());
    }
}
