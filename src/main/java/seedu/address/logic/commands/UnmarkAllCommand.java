package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_STUDENT_LIST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unmark all students in the student list as present.
 */
public class UnmarkAllCommand extends Command {
    public static final String COMMAND_WORD = "unmarkall";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isStudentListEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_STUDENT_LIST);
        }

        model.unmarkAllStudents();

        model.updateFilteredStudentList(student -> false);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED);
    }
}
