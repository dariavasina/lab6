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
    public static void main(String[] args) {

        int BUFFER_SIZE = 1024 * 1024;
        String serverHostname = "localhost";
        int serverPort = 6889;
        while (true) {
            try (Socket socket = new Socket(serverHostname, serverPort);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                //System.out.println("Connected to server");

                Scanner scanner = new Scanner(System.in);

                LinkedHashMap<Long, StudyGroup> collection = new LinkedHashMap<>();

                StudyGroupCollectionManager sgc = new StudyGroupCollectionManager(collection);
                CommandParser cp = new CommandParser(sgc);

                try {
                    System.out.print("Enter a command: ");
                    Triplet<String, String[], StudyGroup> parsedCommand = cp.readCommand(scanner, false);
                    CommandWithResponse command = cp.pack(parsedCommand);
                    Request request = new Request(command);

                    out.writeObject(request);
                    out.flush();
                    //System.out.println("Sent request: " + "infoCommand");

                    Response response = (Response) in.readObject();
                    System.out.println(response.getOutput());
                } catch (InvalidInputException | KeyDoesNotExistException | InvalidArgumentsException |
                         ClassNotFoundException | KeyAlreadyExistsException | CommandDoesNotExistException e) {
                    //System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Please enter a command");
                }
            } catch (IOException e) {
                System.out.println("collectionManagerException");
            }

        }
    }
}