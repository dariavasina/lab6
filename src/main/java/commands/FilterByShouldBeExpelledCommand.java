package commands;

import collection.StudyGroupCollectionManager;

public class FilterByShouldBeExpelledCommand extends Command{
    public FilterByShouldBeExpelledCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().filterByShouldBeExpelled(getShouldBeExpelled());
    }
}
