package common.commands.commandObjects;

import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;
import common.collectionClasses.StudyGroup;
import common.exceptions.KeyDoesNotExistException;

public class UpdateCommand extends Command {
    private Long id;
    private StudyGroup value;

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(StudyGroup value) {
        this.value = value;
    }


    public UpdateCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public UpdateCommand() {
    }

    @Override
    public void execute() {
        try {
            getCollection().updateByID(id, value);
        } catch (KeyDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
