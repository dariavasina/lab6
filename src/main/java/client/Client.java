package client;

import common.commands.Command;
import common.commands.CommandWithResponse;
import common.dataStructures.Triplet;
import common.exceptions.*;
import common.networkStructures.Request;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.collectionClasses.StudyGroup;

import common.io.consoleIO.CommandParser;

import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        int BUFFER_SIZE = 1024 * 1024;

        InetAddress address = null;
        int port = 0;

        try {
            address = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host. Please try again");
            System.exit(0);
        }

        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Port must be a number, please try again");
        }

        ScriptExecutor scriptExecutor = new ScriptExecutor();
        while (true) {
            try (Socket socket = new Socket(address, port);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                socket.setSoTimeout(500);

                Scanner scanner = new Scanner(System.in);

                LinkedHashMap<Long, StudyGroup> collection = new LinkedHashMap<>();

                StudyGroupCollectionManager sgc = new StudyGroupCollectionManager(collection);
                CommandParser cp = new CommandParser(sgc);

                try {
                    System.out.print("Enter a command: ");
                    Triplet<String, String[], StudyGroup> parsedCommand = cp.readCommand(scanner, false);
                    String commandName = parsedCommand.getFirst();

                    String[] commandArgs = parsedCommand.getSecond();
                    StudyGroup studyGroup = parsedCommand.getThird();

                    if (commandName.equals("exit")) {
                        if (commandArgs.length == 0) {
                            System.out.println("Bye!");
                            System.exit(0);
                        } else {
                            throw new InvalidArgumentsException("Command exit doesn't take any arguments!");
                        }
                    }

                    else if (commandName.equals("execute_script")) {
                        if (commandArgs.length == 1) {
                            scriptExecutor.executeScript(commandArgs[0], sgc, address, port);
                        } else {
                            throw new InvalidArgumentsException("Command execute_script take only one argument - path to the script");
                        }
                    } else {
                        CommandWithResponse command = cp.pack(parsedCommand);
                        Request request = new Request(command);

                        out.writeObject(request);
                        out.flush();

                        Response response = (Response) in.readObject();
                        if (response == null) {
                            System.out.println("Server is down");
                        }
                        System.out.println(response.getOutput());
                    }


                } catch (InvalidInputException | KeyDoesNotExistException | InvalidArgumentsException |
                         ClassNotFoundException | KeyAlreadyExistsException | CommandDoesNotExistException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    //System.out.println(e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Server is down, please try again");
            }

        }
    }
}