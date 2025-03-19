package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
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

    public FindStudentCommand(StudentIdEqualsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()), true);
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
