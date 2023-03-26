package commands;

import collection.StudyGroupCollectionManager;

public class RemoveKeyCommand extends Command{
    public RemoveKeyCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().removeByKey(getKey());
    }
}
