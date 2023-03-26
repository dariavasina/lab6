package reader;

import collection.StudyGroupCollectionManager;
import commands.CommandExecutor;
import data.StudyGroup;
import exceptions.*;
import file.FileManager;

import java.util.*;

public class CommandParser {
    private final StudyGroupCollectionManager collection;
    private final String filename;
    private FileManager fileManager;


    public CommandParser (StudyGroupCollectionManager collection, FileManager fileManager, String fileName) {
        this.collection = collection;
        this.filename = fileName;
        this.fileManager = fileManager;
    }

    public static Long readKey(String input) throws InvalidInputException {
        long key;
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Key for the element not found");
        }
        else {
            String s = input.split(" ")[1];
            try {
                return Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new InvalidInputException("Key must be a number");
            }
        }
    }

    public Long readId(String input) throws InvalidInputException, IdDoesNotExistException {
        long id;
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("ID for the element not found");
        }
        else {
            String s = input.split(" ")[1];
            try {
                id = Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new InvalidInputException("ID must be a number");
            }
            if (collection.idInCollection(id)) {
                return id;
            } else {
                throw new IdDoesNotExistException("Element with this ID was not found");
            }
        }
    }

    public static Integer readInteger(String input, String valueName) throws InvalidInputException {
        if (input.split(" ").length != 2) {
            throw new InvalidInputException(valueName + " not found" + "\n");
        }
        else {
            String s = input.split(" ")[1];
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(valueName + " must be a number");
            }
        }
    }

    public String getCommandFromConsole(Scanner scanner) {
        return scanner.nextLine();
    }

    public void readCommand(String input, Scanner scanner, boolean fromFile) throws CommandDoesNotExistException, InvalidInputException, KeyDoesNotExistException, KeyAlreadyExistsException {
        List<String> commands = Arrays.asList("help", "info", "show", "insert", "update", "remove_key",
                "clear", "save", "execute_script", "exit", "replace_if_greater", "replace_if_lower", "remove_greater_key",
                "count_by_students_count", "filter_by_should_be_expelled", "print_field_descending_students_count");

        List<String> commandsWithKey = Arrays.asList("insert", "replace_if_greater", "replace_if_lower", "remove_key",
                                        "remove_greater_key");

        List<String> commandsWithStudyGroup = Arrays.asList("insert", "replace_if_greater", "replace_if_lower");

        List<String> commandsWithKeyInCollection = Arrays.asList("remove_key", "replace_if_greater", "replace_if_lower");

        input = input.trim().replaceAll(" +", " ");
        String commandName = input.split(" ")[0];
        CommandExecutor commandExecutor = new CommandExecutor(collection, commandName);

        StudyGroupConsoleReader readerFromConsole = new StudyGroupConsoleReader();
        StudyGroupReader readerFromScript = new StudyGroupReader();

        boolean flag = false;

        if (commandsWithKey.contains(commandName)) {
            Long key = readKey(input);
            commandExecutor.setKey(key);

            if (commandsWithKeyInCollection.contains(commandName)) {
                if (!collection.containsKey(key)) {
                    throw new KeyDoesNotExistException(key.toString());
                }
            }
            else if (commandName.equals("insert")) {
                if (collection.containsKey(key)) {
                    throw new KeyAlreadyExistsException(key.toString());
                }
            }
        }

        if (commandsWithStudyGroup.contains(commandName)) {
            StudyGroup studyGroup;
            if (fromFile) {
                studyGroup = readerFromScript.readStudyGroup(scanner);
            }
            else {
                studyGroup = readerFromConsole.readStudyGroup(scanner);
            }
            commandExecutor.setValue(studyGroup);
        }

        switch (commandName) {
            case "update" -> {
                try {
                    Long id = readId(input);
                    StudyGroup newStudyGroup;
                    if (fromFile) {
                        newStudyGroup = readerFromScript.readStudyGroup(scanner);
                    }
                    else {
                        newStudyGroup = readerFromConsole.readStudyGroup(scanner);
                    }
                    commandExecutor.setId(id);
                    commandExecutor.setValue(newStudyGroup);
                } catch (IdDoesNotExistException e) {
                    System.out.println(e.getMessage());
                    flag = true;
                }
            }

            case "count_by_students_count" -> {
                try {
                    Integer studentsCount = readInteger(input, "studentsCount");
                    commandExecutor.setStudentsCount(studentsCount);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    flag = true;
                }
            }

            case "filter_by_should_be_expelled" -> {
                try {
                    Integer shouldBeExpelled = readInteger(input, "shouldBeExpelled");
                    commandExecutor.setShouldBeExpelled(shouldBeExpelled);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    flag = true;
                }
            }

            case "save" -> {
                commandExecutor.setFileManager(fileManager);
            }

            case "exit" -> {
                commandExecutor.setScanner(scanner);
                commandExecutor.setCollectionFile(filename);
            }

            case "execute_script" -> {
                String fileName = input.split(" ")[1];
                commandExecutor.setFileName(fileName);
            }
        }




        if (!commands.contains(commandName)) {
            throw new CommandDoesNotExistException(commandName);
        }
        else if (!flag) {
            commandExecutor.execute();
        }


    }
}
