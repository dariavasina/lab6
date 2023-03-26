import collection.StudyGroupCollectionManager;
import data.*;
import exceptions.CommandDoesNotExistException;
import exceptions.InvalidInputException;
import exceptions.KeyAlreadyExistsException;
import exceptions.KeyDoesNotExistException;
import file.FileManager;
import reader.CommandParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();

        File tmp = new File(".save.json");

        LinkedHashMap<Long, StudyGroup> collection = new LinkedHashMap<>();

        try{
            String filename = args[0];
            if (tmp.exists()) {
                System.out.print("Would you like to restore the collection from your previous session? (y/n): ");
                String answer = scanner.nextLine();

                if (answer.equals("y")) {
                    try {
                        collection = FileManager.readFromJson(".save.json");
                    } catch (IOException e) {
                        System.out.println("Something went wrong, please try again");
                    }
                }
                else {
                    tmp.delete();
                    try {
                        collection = FileManager.readFromJson(filename);

                    } catch (IOException e) {
                        System.out.println("Please provide an existing filename");
                    }
                }
            }
            else {
                try {
                    collection = FileManager.readFromJson(filename);
                } catch (IOException e) {
                    System.out.println("Please provide an existing filename");
                }
            }

            try {
                StudyGroupCollectionManager sgc = new StudyGroupCollectionManager(collection, filename);

                CommandParser cp = new CommandParser(sgc, fileManager, filename);

                while (true) {
                    try {
                        System.out.print("Enter a command: ");
                        cp.readCommand(cp.getCommandFromConsole(scanner), scanner, false);
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                    } catch (KeyDoesNotExistException | KeyAlreadyExistsException | CommandDoesNotExistException e) {
                        //
                    }
                }
            } catch (IOException e) {
                //
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide the name of the file with collection");
        }



    }
}
