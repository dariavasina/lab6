package server.collectionManagement;
import common.exceptions.*;
import common.collectionClasses.StudyGroup;
import common.json.FileManager;
import common.io.consoleIO.CommandParser;

import java.io.*;
import java.util.*;


public class StudyGroupCollectionManager extends CollectionManager<Long, StudyGroup> {
    private HashSet<String> executedScripts = new HashSet<>();
    private FileManager fileManager;
    private String collectionFile;
    public StudyGroupCollectionManager(Map<Long, StudyGroup> collection) throws IOException {
        super(collection);
        fileManager = new FileManager();
    }
    public StudyGroupCollectionManager(Map<Long, StudyGroup> collection, String collectionFile) throws IOException {
        this(collection);
        this.fileManager = new FileManager();
        this.collectionFile = collectionFile;
    }


    @Override
    public void updateByID(Long id, StudyGroup newStudyGroup) throws KeyDoesNotExistException {
        Map<Long, StudyGroup> collection = getMap();
        if (idInCollection(id)) {
            for (Long key : collection.keySet()) {
                if (Objects.equals(collection.get(key).getId(), id)) {
                    newStudyGroup.setId(id);
                    collection.put(key, newStudyGroup);
                    //System.out.printf("Element with ID %s successfully updated\n", id);
                }
            }
        }
        else {
            throw new KeyDoesNotExistException(id.toString());
        }
    }

    @Override
    public void removeByKey(Long key) {
        if (containsKey(key)) {
            getMap().remove(key);
            //System.out.printf("Element with key %s has been successfully removed\n", key);
        }
    }

    @Override
    public StringBuilder show() throws EmptyCollectionException {
        StringBuilder output = new StringBuilder();
        Map<Long, StudyGroup> collection = getMap();
        if (collection.isEmpty()) {
            throw new EmptyCollectionException();
        }
        else {
            for (Long key : collection.keySet()) {
                output.append(key).append("\n").append(collection.get(key));
            }
        }
        return output;
    }

    public StringBuilder info() {
        StringBuilder output = new StringBuilder();
        output.append("Number of elements in the collection: ").append(getMap().size()).append("\n");
        output.append("Initialization date: ").append(getInitializationDate());
        return output;
    }

    public void replaceIfLower(Long keyToChange, StudyGroup value) {
        boolean changed = false;
        Map<Long, StudyGroup> collection = getMap();
        if (collection.containsKey(keyToChange)) {
            for (Long key : collection.keySet()) {
                if (Objects.equals(key, keyToChange)) {
                    if (value.compareTo(collection.get(key)) < 0) {
                        getMap().put(key, value);
                        System.out.println("The value by the key " + key + " has been changed");
                        changed = true;
                    }
                }
            }
        }
        if (!changed) {
            System.out.println("The value by the key " + keyToChange + " was not changed");
        }
    }

    public void replaceIfGreater(Long keyToChange, StudyGroup value) {
        boolean changed = false;
        Map<Long, StudyGroup> collection = getMap();
        if (collection.containsKey(keyToChange)) {
            for (Long key : collection.keySet()) {
                if (Objects.equals(key, keyToChange)) {
                    if (value.compareTo(collection.get(key)) > 0) {
                        getMap().put(key, value);
                        System.out.println("The value by the key " + key + " has been changed");
                        changed = true;
                    }
                }
            }
        }
        if (!changed) {
            System.out.println("The value by the key " + keyToChange + " was not changed");
        }
    }

    public int countByStudentsCount(Integer studentsCount) {
        int count = 0;
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(collection.get(key).getStudentsCount(), studentsCount)) {
                count += 1;
            }
        }
        return count;
        //System.out.println("There are " + count + " study groups in the collection with " + studentsCount + " students");
    }

    public boolean idInCollection(Long id) {
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(collection.get(key).getId(), id)) {
                return true;
            }
        }
        return false;
    }

    public void removeGreaterKey(Long key) {
        boolean flag = false;
        Map<Long, StudyGroup> collection = getMap();
        for (Long k : collection.keySet()) {
            if (k > key) {
                removeByKey(k);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("There are no elements in the collection with key greater than " + key);
        }
    }

    public StringBuilder filterByShouldBeExpelled (Integer shouldBeExpelled) {
        StringBuilder output = new StringBuilder();
        Map<Long, StudyGroup> collection = getMap();
        boolean found = false;
        for (Long key : collection.keySet()) {
            StudyGroup group = collection.get(key);
            if (Objects.equals(group.getShouldBeExpelled(), shouldBeExpelled)) {
                output.append(group);
                found = true;
            }
        }
        if (!found) {
            output.append("There are no elements in the collection with ").append(shouldBeExpelled).append(" shouldBeExpelled");
        }
        return output;
    }

    public StringBuilder printFieldDescendingStudentsCount () {
        StringBuilder output = new StringBuilder();
        HashMap<Long, StudyGroup> collection = getMap();
        ArrayList<StudyGroup> list = new ArrayList<>();
        for (Map.Entry<Long, StudyGroup> entry : collection.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<StudyGroup>() {
            @Override
            public int compare(StudyGroup g1, StudyGroup g2) {
                return -(g1.compareTo(g2));
            }
        });
        for (StudyGroup g : list) {
            output.append(g.getStudentsCount());
        }
        return output;
    }

    public void executeCommandsFromFile(String fileName) throws CommandExecutionException, FileAccessException {
        executedScripts.add(fileName);
        try {
            File file = new File(fileName);
            if (!file.exists() || !file.canRead()) {
                throw new FileAccessException("Cannot read file: " + fileName);
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(" ", 2);
                String commandName = parts[0];
                String argument = parts.length > 1 ? parts[1] : "";
                if (commandName.equalsIgnoreCase("execute_script")) {
                    String scriptFileName = argument;
                    if (executedScripts.contains(scriptFileName)) {
                        throw new CommandExecutionException("Recursion detected while executing script: " + scriptFileName);
                    }
                    executedScripts.add(scriptFileName);
                    executeCommandsFromFile(scriptFileName);
                    executedScripts.remove(scriptFileName);
                }

                //CommandParser cp = new CommandParser(this, new FileManager(), collectionFile);
                CommandParser cp = new CommandParser(this);
                try {
                    cp.readCommand(scanner, true);
                } catch (InvalidInputException | CommandDoesNotExistException | KeyAlreadyExistsException |
                         KeyDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileAccessException("Cannot read file: " + fileName);
        }
        executedScripts.remove(fileName);
    }
}
