package common.commands;

import common.exceptions.EmptyCollectionException;
import common.exceptions.InvalidArgumentsException;
import server.collectionManagement.StudyGroupCollectionManager;
import common.collectionClasses.StudyGroup;
import common.json.FileManager;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Command implements Serializable {
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

    private String[] args;
    private StudyGroup studyGroup;

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) throws InvalidArgumentsException {
        this.args = args;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public abstract void execute() throws EmptyCollectionException;

}
