package commands;

import collection.StudyGroupCollectionManager;
import data.StudyGroup;
import file.FileManager;

import java.util.Scanner;

public abstract class Command {
    private Long key;
    private StudyGroup value;
    private Integer studentsCount;
    private Integer shouldBeExpelled;
    private Long id;
    private Scanner scanner;
    private String fileName;
    private String collectionFile;

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setCollectionFile(String collectionFile) {
        this.collectionFile = collectionFile;
    }

    public String getCollectionFile() {
        return collectionFile;
    }

    private FileManager fileManager;

    public StudyGroupCollectionManager getCollection() {
        return collection;
    }

    public void setCollection(StudyGroupCollectionManager collection) {
        this.collection = collection;
    }

    private StudyGroupCollectionManager collection;

    public Command() {};

    public Command(StudyGroupCollectionManager collection) {
        this.collection = collection;
    }



    public abstract void execute();

    public void setKey(Long key) {
        this.key = key;
    }

    public void setValue(StudyGroup value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentsCount(Integer field) {
        this.studentsCount = field;
    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public Long getKey() {
        return key;
    }

    public StudyGroup getValue() {
        return value;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
