package common.commands.commandObjects;

import common.commands.CommandWithResponse;
import common.networkStructures.Response;
import server.collectionManagement.StudyGroupCollectionManager;
import common.commands.Command;

public class HelpCommand extends CommandWithResponse {
    public HelpCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    public HelpCommand() {
    }

    @Override
    public void execute() {
        /*System.out.println("""
                help: output help for available commands
                info: output to the standard output stream information about the collection (type, initialization date, number of elements, etc.)
                show: output to the standard output stream all elements of the collection in the string representation
                insert <id> {element}: add a new element with the specified key
                update <id> {element}: update the value of a collection element whose id is equal to the specified
                remove_key <id>: delete an element from the collection by its key
                clear: clear the collection
                save: save the collection to a file
                execute_script <file_name>: read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode. USE RELATIVE PATHS.
                exit: terminate the program (without saving to a file)
                replace_if_greater <key> {element}: replace the value by key if the new value is greater than the old one
                replace_if_lower <id> {element}: replace the value by key if the new value is less than the old one.
                remove_greater_key <key>: remove all items from the collection whose key exceeds the specified one
                count_by_students_count <studentsCount> : print the number of elements whose studentsCount field value is equal to the specified
                filter_by_should_be_expelled <shouldBeExpelled> : output elements whose value of the field shouldBeExpelled is equal to the specified
                print_field_descending_students_count: print the values of the studentsCount field of all elements in descending order""");
         */
    }

    @Override
    public Response getCommandResponse() {
        return new Response("to be done...");
    }
}
