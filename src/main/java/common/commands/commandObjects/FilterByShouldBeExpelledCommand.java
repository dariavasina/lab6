package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.exceptions.InvalidArgumentsException;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class FilterByShouldBeExpelledCommand extends CommandWithResponse {
    StringBuilder output;
    public FilterByShouldBeExpelledCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public FilterByShouldBeExpelledCommand() {
    }

    @Override
    public void setArgs(String[] args) throws InvalidArgumentsException {
        try {
            Integer shouldBeExpelled = Integer.parseInt(args[0]);
            super.setArgs(new String[]{String.valueOf(shouldBeExpelled)});
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("shouldBeExpelled must be a number! Please try to enter a command again");
        }
    }

    @Override
    public void execute() {
        Integer shouldBeExpelled = Integer.parseInt(getArgs()[0]);
        output = getCollection().filterByShouldBeExpelled(shouldBeExpelled);
    }

    @Override
    public Response getCommandResponse() {
        return new Response(output.toString());
    }
}
