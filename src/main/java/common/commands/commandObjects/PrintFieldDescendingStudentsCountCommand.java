package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class PrintFieldDescendingStudentsCountCommand extends CommandWithResponse {
    public PrintFieldDescendingStudentsCountCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public PrintFieldDescendingStudentsCountCommand() {
    }

    @Override
    public void execute() {
        getCollection().printFieldDescendingStudentsCount();
    }

    @Override
    public Response getCommandResponse() {
        return new Response("to be done...");
    }
}
