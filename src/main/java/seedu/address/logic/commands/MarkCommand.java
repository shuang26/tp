package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ATTENDANCE_MARKED;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentIdEqualsPredicate;

/**
 * Marks a student identified using their student id as present.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the student identified as present by the "
            + "student id as present.\nParameters: [STUDENT_ID]\n"
            + "Example: " + COMMAND_WORD + " A01A";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Mark command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Student ID: %1$s";

    private final StudentId id;

    /**
     * Initialises MarkCommand with given student id.
     * @param id ID of student to mark attendance.
     */
    public MarkCommand(StudentId id) {
        requireAllNonNull(id);

        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert (id != null);

        model.updateFilteredStudentList(new StudentIdEqualsPredicate(id));
        List<Student> students = model.getFilteredStudentList();

        if (students.isEmpty()) {
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
            throw new CommandException("Requested student was not found in the student list.");
        }

        Student studentToMark = students.get(0);
        studentToMark.setPresent();

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_STUDENT_ATTENDANCE_MARKED, id));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand e = (MarkCommand) other;
        return id.equals(e.id);
    }
}
