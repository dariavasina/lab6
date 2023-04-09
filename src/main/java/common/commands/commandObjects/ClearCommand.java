package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;


public class ClearCommand extends CommandWithResponse {
    public ClearCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ClearCommand() {
    }

    @Override
    public void execute() {
        getCollection().clear();
    }

    @Override
    public Response getCommandResponse() {
        return new Response("clear command finished successfully");
    }
}
