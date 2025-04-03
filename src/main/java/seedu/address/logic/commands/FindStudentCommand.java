package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_NOT_FOUND;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_FOUND;

import java.util.NoSuchElementException;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentIdEqualsPredicate;

/**
 * Finds and lists a student in CareBook whose student ID matches.
 * Student ID matching is case-sensitive (must be in uppercase).
 */
public class FindStudentCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds a specific student by their exact "
            + "STUDENT ID and displays their full details.\n"
            + "PARAMETERS: " + "STUDENT ID\n"
            + "Example: " + COMMAND_WORD + " A01A";

    private final StudentIdEqualsPredicate predicate;
    private final StudentId studentId;

    /**
     * Initialises FindStudentCommand with given student id.
     * @param studentId ID of student to find.
     */
    public FindStudentCommand(StudentId studentId) {
        this.studentId = studentId;
        this.predicate = new StudentIdEqualsPredicate(studentId);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert (studentId != null);

        try {
            model.updateFilteredStudentList(predicate);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_NOT_FOUND);
        }

        return new CommandResult(MESSAGE_STUDENT_FOUND, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindStudentCommand)) {
            return false;
        }

        FindStudentCommand otherFindStudentCommand = (FindStudentCommand) other;
        return predicate.equals(otherFindStudentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
