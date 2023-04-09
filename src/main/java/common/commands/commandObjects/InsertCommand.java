package common.commands.commandObjects;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class InsertCommand extends Command {
    public InsertCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public InsertCommand() {
    }

    @Override
    public void execute() {
        //getCollection().insert(getKey(), getValue());
        //System.out.println("Element with key " + getKey().toString() + " added to collection");
    }
}
