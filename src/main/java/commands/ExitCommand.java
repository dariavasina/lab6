package commands;

import collection.StudyGroupCollectionManager;
import file.FileManager;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        boolean isSaved = FileManager.filesAreEqual(getCollectionFile(), ".save.json");
        if (!isSaved) {
            System.out.println("Are you sure you want to exit without saving? (y/n)");
            String answer = getScanner().nextLine();
            if (answer.equals("y")) {
                getFileManager().deleteTempFile();
                System.exit(0);
            }
        }
        else {
            getFileManager().deleteTempFile();
            System.exit(0);
        }
    }
}
