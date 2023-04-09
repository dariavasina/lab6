package common.commands;

import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;

public abstract class CommandWithResponse extends Command{

    public CommandWithResponse(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public CommandWithResponse() {};

    public abstract Response getCommandResponse();
}
