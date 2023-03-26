package commands;
import collection.StudyGroupCollectionManager;

public class InsertCommand extends Command {
    public InsertCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().insert(getKey(), getValue());
        System.out.println("Element with key " + getKey().toString() + " added to collection");
    }
}
