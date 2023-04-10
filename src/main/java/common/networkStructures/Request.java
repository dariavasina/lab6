package common.networkStructures;
import common.commands.Command;
import common.commands.CommandWithResponse;

import java.io.Serializable;
import java.net.SocketAddress;

public class Request implements Serializable {


    private CommandWithResponse command;

    private SocketAddress host;

    public Request(CommandWithResponse command) {
        this.command = command;
    }


    public CommandWithResponse getCommand() {
        return command;
    }

    public void setCommand(CommandWithResponse command) {
        this.command = command;
    }


    public SocketAddress getHost() {
        return host;
    }

    public void setHost(SocketAddress host) {
        this.host = host;
    }
}