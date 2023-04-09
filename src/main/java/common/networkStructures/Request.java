package common.networkStructures;
import common.commands.Command;

import java.io.Serializable;
import java.net.SocketAddress;

public class Request implements Serializable {


    private Command command;

    private SocketAddress host;

    public Request(Command command) {
        this.command = command;
    }


    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }


    public SocketAddress getHost() {
        return host;
    }

    public void setHost(SocketAddress host) {
        this.host = host;
    }
}