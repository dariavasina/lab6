package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.exceptions.IdDoesNotExistException;
import common.exceptions.InvalidArgumentsException;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;
import common.collectionClasses.StudyGroup;
import common.exceptions.KeyDoesNotExistException;

public class UpdateCommand extends CommandWithResponse {
    private Long id;
    private StudyGroup value;

    public UpdateCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public UpdateCommand() {
    }

    @Override
    public void setArgs(String[] args) throws InvalidArgumentsException {
        try {
            id = Long.parseLong(args[0]);
            super.setArgs(new String[]{String.valueOf(id)});
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("The id must be a number! Please try to enter a command again");
        }
    }

    @Override
    public Response getCommandResponse() {
        return new Response("update finished successfully");
    }

    @Override
    public void execute() throws IdDoesNotExistException {
        StudyGroup value = getStudyGroup();
        getCollection().updateByID(id, value);
    }
}
