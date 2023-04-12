package client;

import common.collectionClasses.StudyGroup;
import common.dataStructures.Triplet;
import common.exceptions.*;
import common.io.consoleIO.CommandParser;
import common.json.FileManager;
import common.networkStructures.Request;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class ScriptExecutor {
    private HashSet<String> executedScripts = new HashSet<>();

    public void executeScript(String filename, StudyGroupCollectionManager manager, InetAddress address, int port) throws ScriptRecursionException, FileAccessException {
        executedScripts.add(filename);

        CommandParser commandParser = new CommandParser(manager);
        try {
            File file = new File(filename);
            if (!file.exists() || !file.canRead()) {
                throw new FileAccessException("Cannot read file: " + filename);
            }
            Scanner scanner = new Scanner(file);
            while (true) {
                try (Socket socket = new Socket(address, port);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

                    Triplet<String, String[], StudyGroup> parsedCommand = commandParser.readCommand(scanner, true);
                    String commandName = parsedCommand.getFirst();
                    String[] args = parsedCommand.getSecond();
                    if (commandName.equals("execute_script")) {
                        if (executedScripts.contains(args[0])) {
                            throw new ScriptRecursionException("You should not call execute_script recursively!");
                        }
                        executedScripts.add(args[0]);
                        executeScript(args[0], manager, address, port);
                    } else {
                        Request request = new Request(commandParser.pack(parsedCommand));
                        out.writeObject(request);
                        out.flush();

                        Response response = (Response) in.readObject();
                        System.out.println(response.getOutput());

                        if (response == null) {
                            System.out.println("Server is down\nPlease try again later");
                        } else {
                            System.out.println(response.getOutput());
                        }
                    }
                } catch (CommandDoesNotExistException | ScriptRecursionException | InvalidInputException |
                         KeyDoesNotExistException
                         | InvalidArgumentsException | ClassNotFoundException | KeyAlreadyExistsException e) {
                    System.out.println(e.getMessage());

                } catch (Exception e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

