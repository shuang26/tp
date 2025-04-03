package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ATTENDANCE_MARKED;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ID_NOT_FOUND;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.NoSuchElementException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Marks a student identified using their student id as present.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the student identified as present by the "
            + "student ID as present.\nParameters: STUDENT_ID\n"
            + "Example: " + COMMAND_WORD + " A01A";

    private final StudentId studentId;

    /**
     * Initialises MarkCommand with given student id.
     * @param studentId ID of student to mark attendance.
     */
    public MarkCommand(StudentId studentId) {
        requireAllNonNull(studentId);

        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert (studentId != null);

        try {
            Student studentToMark = model.getStudentById(studentId);
            studentToMark.setPresent();
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ID_NOT_FOUND, studentId));
        }

        model.updateFilteredStudentList(student -> false);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_STUDENT_ATTENDANCE_MARKED, studentId));
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
        return studentId.equals(e.studentId);
    }
}
