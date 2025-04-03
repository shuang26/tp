package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_FIND_LIST;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ID_NOT_FOUND;

import seedu.address.commons.util.ToStringBuilder;
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
            + "(case sensitive) STUDENT ID and displays their full details.\n"
            + "PARAMETERS: " + "STUDENT ID (must be in uppercase)\n"
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
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        if (!model.getFilteredStudentList().isEmpty()) {
            return new CommandResult(
                    String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                            model.getFilteredStudentList().size()), true);
        } else {
            return new CommandResult(String.format(MESSAGE_STUDENT_ID_NOT_FOUND, studentId)
                    + MESSAGE_FIND_LIST);
        }
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
