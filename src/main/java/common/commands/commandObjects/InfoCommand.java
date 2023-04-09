package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class InfoCommand extends CommandWithResponse {
    public InfoCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }
    private StringBuilder output;

    public InfoCommand() {
    }

    @Override
    public void execute() {
        output = getCollection().info();
    }

    @Override
    public Response getCommandResponse() {
        return new Response(output.toString());
    }
}
