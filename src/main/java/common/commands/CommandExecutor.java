package common.commands;

import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.commandObjects.*;
import common.collectionClasses.StudyGroup;
import common.json.FileManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor {
    private StudyGroupCollectionManager collection;
    private Response response;


    public CommandExecutor(StudyGroupCollectionManager collection) {
        this.collection = collection;
    }


    public void execute(CommandWithResponse command) {
        command.setCollection(collection);
        command.execute();
        response = command.getCommandResponse();
    }

    public Response getResponse() {
        return response;
    }
}
