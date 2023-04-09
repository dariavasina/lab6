package common.commands.commandObjects;

import common.exceptions.InvalidArgumentsException;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class CountByStudentsCountCommand extends Command {
    public CountByStudentsCountCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public CountByStudentsCountCommand() {
    }

    @Override
    public void setArgs(String[] args) throws InvalidArgumentsException {
        try {
            Integer studentsCount = Integer.parseInt(args[0]);
            super.setArgs(new String[]{String.valueOf(studentsCount)});
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException("The studentsCount must be a number! Please try to enter a command again");
        }
    }

    @Override
    public void execute() {
        Integer studentsCount = Integer.parseInt(getArgs()[0]);
        getCollection().countByStudentsCount(studentsCount);
    }
}
