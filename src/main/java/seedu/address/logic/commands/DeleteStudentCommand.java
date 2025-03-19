package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Deletes a student identified using their student ID from CareBook.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specific student by their exact (case sensitive) STUDENT ID.\n"
            + "Parameters: STUDENT_ID (must be in uppercase)\n"
            + "Example: " + COMMAND_WORD + " A01A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s ";
    public static final String MESSAGE_STUDENT_ID_NOT_FOUND = "No student with student ID: %1$s found.";

    private final StudentId targetStudentId;

    public DeleteStudentCommand(StudentId targetStudentId) {
        this.targetStudentId = targetStudentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person studentToDelete = null;
        for (Person student : lastShownList) {
            if (student.getStudentId().equals(targetStudentId)) {
                studentToDelete = student;
                break;
            }
        }
        if (studentToDelete == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ID_NOT_FOUND, targetStudentId));
        }

        model.deletePerson(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(studentToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteStudentCommand otherDeleteCommand = (DeleteStudentCommand) other;
        return targetStudentId.equals(otherDeleteCommand.targetStudentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetStudentId", targetStudentId)
                .toString();
    }
}
