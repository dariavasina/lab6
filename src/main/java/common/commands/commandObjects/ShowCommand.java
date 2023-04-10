package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.exceptions.EmptyCollectionException;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class ShowCommand extends CommandWithResponse {
    private StringBuilder output;
    public ShowCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public ShowCommand() {
    }

    @Override
    public void execute() throws EmptyCollectionException {
        output = getCollection().show();
    }

    @Override
    public Response getCommandResponse() {
        return new Response(output.toString());
    }
}
