package server;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import common.commands.CommandExecutor;
import common.commands.CommandWithResponse;
import common.exceptions.EmptyCollectionException;
import common.io.consoleIO.CommandParser;
import common.io.consoleIO.ConfirmationReader;
import common.json.FileManager;
import common.networkStructures.Request;
import common.networkStructures.Response;
import server.collectionManagement.CollectionManager;
import server.collectionManagement.StudyGroupCollectionManager;

public class Server {
    public static void main(String[] args) throws IOException {

        int BUFFER_SIZE = 1024 * 1024;
        int port = 6889;

        String clientsDataPath = "src/main/java/server/clientsData/collection.json";
        StudyGroupCollectionManager collectionManager = new StudyGroupCollectionManager(FileManager.readFromJson(clientsDataPath));
        CommandExecutor commandExecutor = new CommandExecutor(collectionManager);
        CommandParser commandParser = new CommandParser(collectionManager);

        Scanner scanner = new Scanner(System.in);
        ConfirmationReader confirmationReader = new ConfirmationReader();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket.getInetAddress());
                    Thread clientThread = new Thread(new ClientHandler(clientSocket, commandExecutor, collectionManager));
                    clientThread.start();
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final CommandExecutor commandExecutor;
        private final StudyGroupCollectionManager collectionManager;

        public ClientHandler(Socket clientSocket, CommandExecutor commandExecutor, StudyGroupCollectionManager collectionManager) {
            this.clientSocket = clientSocket;
            this.commandExecutor = commandExecutor;
            this.collectionManager = collectionManager;
        }

        @Override
        public void run() {
            try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                Request request = (Request) in.readObject();
                System.out.println("Received request: " + request.getCommand());

                CommandWithResponse command = request.getCommand();
                commandExecutor.setCollection(collectionManager);
                commandExecutor.execute(command);

                Response response = commandExecutor.getCommandResponse();
                out.writeObject(response);
                out.flush();
                System.out.println("Sent response: " + response.getOutput());

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            } catch (EmptyCollectionException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}