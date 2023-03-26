package commands;

import collection.StudyGroupCollectionManager;

import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        try {
            getFileManager().saveToJson(getCollection(), "collection.json");
            System.out.println("Collection was successfully saved to file");
        } catch (IOException e) {
            System.out.println("Please provide an existing filename");
        }
    }
}
