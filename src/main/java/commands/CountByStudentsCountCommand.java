package commands;

import collection.StudyGroupCollectionManager;

public class CountByStudentsCountCommand extends Command{
    public CountByStudentsCountCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().countByStudentsCount(getStudentsCount());
    }
}
