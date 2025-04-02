package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_NOT_FOUND;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ATTENDANCE_UNMARKED;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Marks a student identified using their student id as absent.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the student identified as absent by the "
            + "student id as present.\nParameters: [STUDENT_ID]\n"
            + "Example: " + COMMAND_WORD + " A01A";

    private final StudentId studentId;

    /**
     * Initialises UnmarkCommand with given student id.
     * @param studentId ID of student to unmark attendance.
     */
    public UnmarkCommand(StudentId studentId) {
        requireAllNonNull(studentId);

        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert studentId != null;

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> lastShownList = model.getFilteredStudentList();

        try {
            Student studentToMark = model.getStudentById(studentId, lastShownList);
            studentToMark.setAbsent();
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_NOT_FOUND);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_STUDENT_ATTENDANCE_UNMARKED, studentId));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        UnmarkCommand e = (UnmarkCommand) other;
        return studentId.equals(e.studentId);
    }
}
