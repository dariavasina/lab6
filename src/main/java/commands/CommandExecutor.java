package commands;

import collection.StudyGroupCollectionManager;
import data.StudyGroup;
import file.FileManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor {
    private StudyGroupCollectionManager collection;
    private String commandName;
    private Long key;
    private Long id;
    private StudyGroup value;
    private Integer studentsCount;
    private Integer shouldBeExpelled;
    private Scanner scanner;
    private FileManager fileManager;
    private String fileName;
    private String collectionFile;

    public CommandExecutor(StudyGroupCollectionManager collection, String commandName) {
        this.collection = collection;
        this.commandName = commandName;
        this.fileManager = new FileManager();
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public void setCollectionFile(String filename) {
        this.collectionFile = filename;
    }

    public void setValue(StudyGroup value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void execute() {
        Command helpCommand = new HelpCommand(collection);
        Command infoCommand = new InfoCommand(collection);
        Command showCommand = new ShowCommand(collection);
        Command insertCommand = new InsertCommand(collection);
        Command updateCommand = new UpdateCommand(collection);
        Command removeKeyCommand = new RemoveKeyCommand(collection);
        Command clearCommand = new ClearCommand(collection);
        Command saveCommand = new SaveCommand(collection);
        Command executeScriptCommand = new ExecuteScriptCommand(collection);
        Command exitCommand = new ExitCommand(collection);
        Command replaceIfGreaterCommand = new ReplaceIfGreaterCommand(collection);
        Command replaceIfLowerCommand = new ReplaceIfLowerCommand(collection);
        Command removeGreaterKeyCommand = new RemoveGreaterKeyCommand(collection);
        Command countByStudentsCountCommand = new CountByStudentsCountCommand(collection);
        Command filterByShouldBeExpelledCommand = new FilterByShouldBeExpelledCommand(collection);
        Command printFieldDescendingStudentsCountCommand = new PrintFieldDescendingStudentsCountCommand(collection);


        Map<String, Command> commands = new HashMap<String, Command>();

        commands.put("help", helpCommand);
        commands.put("info", infoCommand);
        commands.put("show", showCommand);
        commands.put("insert", insertCommand);
        commands.put("update", updateCommand);
        commands.put("remove_key", removeKeyCommand);
        commands.put("clear", clearCommand);
        commands.put("save", saveCommand);
        commands.put("execute_script", executeScriptCommand);
        commands.put("exit", exitCommand);
        commands.put("replace_if_greater", replaceIfGreaterCommand);
        commands.put("replace_if_lower", replaceIfLowerCommand);
        commands.put("remove_greater_key", removeGreaterKeyCommand);
        commands.put("count_by_students_count", countByStudentsCountCommand);
        commands.put("filter_by_should_be_expelled", filterByShouldBeExpelledCommand);
        commands.put("print_field_descending_students_count", printFieldDescendingStudentsCountCommand);

        Command command = commands.get(commandName);
        if (key != null) {
            command.setKey(key);
        }

        if (id != null) {
            command.setId(id);
        }

        if (value != null) {
            command.setValue(value);
        }

        if (shouldBeExpelled != null) {
            command.setShouldBeExpelled(shouldBeExpelled);
        }

        if (studentsCount != null) {
            command.setStudentsCount(studentsCount);
        }

        if (scanner != null) {
            command.setScanner(scanner);
        }

        if (fileManager != null) {
            command.setFileManager(fileManager);
        }

        if (fileName != null) {
            command.setFileName(fileName);
        }

        if (collectionFile != null) {
            command.setCollectionFile(collectionFile);
        }

        command.execute();

        if (!commandName.equals("exit")) {
            try {
                fileManager.saveToJson(collection, ".save.json");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
