package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unmark all students in the student list as present.
 */
public class UnmarkAllCommand extends Command {
    public static final String COMMAND_WORD = "unmarkall";

    public static final String MESSAGE_EMPTY_STUDENT_LIST = "No students found in the student list";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isStudentListEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_STUDENT_LIST);
        }

        model.unmarkAllStudents();

        return new CommandResult(MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED);
    }
}
