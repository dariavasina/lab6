package common.commands;

import common.networkStructures.Response;

public abstract class CommandWithResponse extends Command{

    public abstract Response getCommandResponse();
}
