package server;

import common.networkStructures.Request;
import common.commands.Command;

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
            try (ServerSocket serv = new ServerSocket(port)) {
                clientSocket = serv.accept();
                try (InputStream inputStream = clientSocket.getInputStream();
                     ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

                    while (true) {
                        Request request = (Request) objectInputStream.readObject();
                        Command command = request.getCommand();
                        command.execute();
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