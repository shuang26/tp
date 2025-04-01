package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Marks all students in the students list as present.
 */
public class MarkAllCommand extends Command {
    public static final String COMMAND_WORD = "markall";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> students = model.getAddressBook().getStudentList();

        if (students.isEmpty()) {
            throw new CommandException("No students found in the student list.");
        }

        for (int i = 0; i < students.size(); i++) {
            students.get(i).setPresent();
        }

        model.updateFilteredStudentList(student -> false);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED);
    }
}
