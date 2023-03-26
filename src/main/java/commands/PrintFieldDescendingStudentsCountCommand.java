package commands;

import collection.StudyGroupCollectionManager;

public class PrintFieldDescendingStudentsCountCommand extends Command{
    public PrintFieldDescendingStudentsCountCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().printFieldDescendingStudentsCount();
    }
}
