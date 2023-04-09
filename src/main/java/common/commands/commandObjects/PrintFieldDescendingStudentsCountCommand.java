package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class PrintFieldDescendingStudentsCountCommand extends Command {
    public PrintFieldDescendingStudentsCountCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public PrintFieldDescendingStudentsCountCommand() {
    }

    @Override
    public void execute() {
        getCollection().printFieldDescendingStudentsCount();
    }
}
