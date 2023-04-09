package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.exceptions.InvalidArgumentsException;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class RemoveGreaterKeyCommand extends CommandWithResponse {
    public RemoveGreaterKeyCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public RemoveGreaterKeyCommand() {
    }

    @Override
    public void setArgs(String[] args) throws InvalidArgumentsException {
        try {
            Long key = Long.parseLong(args[0]);
            super.setArgs(new String[]{String.valueOf(key)});
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("The key must be a number! Please try to enter a command again");
        }
    }

    @Override
    public void execute() {
        Long key = Long.parseLong(getArgs()[0]);
        getCollection().removeGreaterKey(key);
    }

    @Override
    public Response getCommandResponse() {
        return new Response("remove_greater_key finished successfully");
    }
}
