package server;

import common.commands.CommandExecutor;
import common.io.consoleIO.CommandParser;
import common.io.consoleIO.ConfirmationReader;
import common.json.FileManager;
import common.networkStructures.Request;
import common.commands.Command;
import server.collectionManagement.CollectionManager;
import server.collectionManagement.StudyGroupCollectionManager;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws IOException {

        while (true) {
            int BUFFER_SIZE = 1024 * 1024;
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            Socket clientSocket;
            OutputStream os;
            InputStream is;
            InetAddress host = InetAddress.getByName("localhost");

            int port = 6888;

            String clientsDataPath = "src/main/java/server/clientsData/collection.json";
            StudyGroupCollectionManager collectionManager = new StudyGroupCollectionManager(FileManager.readFromJson(clientsDataPath));
            CommandExecutor commandExecutor = new CommandExecutor(collectionManager);
            CommandParser commandParser = new CommandParser(collectionManager);

            Scanner scanner = new Scanner(System.in);
            ConfirmationReader confirmationReader = new ConfirmationReader();

            try (ServerSocket serv = new ServerSocket(port)) {
                clientSocket = serv.accept();
                try (InputStream inputStream = clientSocket.getInputStream();
                     ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

                    while (true) {
                        Request request = (Request) objectInputStream.readObject();
                        Command command = request.getCommand();
                        commandExecutor.setCollection(collectionManager);
                        commandExecutor.execute(command);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}