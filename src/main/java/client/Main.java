package client;

import common.commands.Command;
import common.dataStructures.Triplet;
import common.networkStructures.Request;
import server.collectionManagement.StudyGroupCollectionManager;
import common.collectionClasses.StudyGroup;
import common.exceptions.CommandDoesNotExistException;
import common.exceptions.InvalidInputException;
import common.exceptions.KeyAlreadyExistsException;
import common.exceptions.KeyDoesNotExistException;
import common.io.consoleIO.CommandParser;

import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException {

        int BUFFER_SIZE = 1024 * 1024;
        ObjectOutputStream objectOutputStream = null;

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(out);

            Scanner scanner = new Scanner(System.in);

            LinkedHashMap<Long, StudyGroup> collection = new LinkedHashMap<>();

            StudyGroupCollectionManager sgc = new StudyGroupCollectionManager(collection);
            CommandParser cp = new CommandParser(sgc);

            while (true) {
                try {
                    System.out.print("Enter a command: ");
                    Triplet<String, String[], StudyGroup> parsedCommand = cp.readCommand(scanner, false);
                    Command command = cp.pack(parsedCommand);
                    Request request = new Request(command);
                    objectOutputStream.writeObject(request);

                    InputStream is;
                    OutputStream os;
                    InetAddress host = InetAddress.getByName("localhost");

                    int port;
                    port = 6888;

                    try (Socket sock = new Socket(host, port)) {
                        os = sock.getOutputStream();
                        os.write(out.toByteArray());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } catch (CommandDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
