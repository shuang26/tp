package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_DELETE_SUCCESS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_NOT_FOUND;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.NoSuchElementException;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Deletes a student identified using their student ID from CareBook.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specific student by their exact (case sensitive) STUDENT ID.\n"
            + "Parameters: STUDENT_ID\n"
            + "Example: " + COMMAND_WORD + " A01A";

    private final StudentId studentId;

    public DeleteStudentCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert(studentId != null);

        try {
            Student studentToDelete = model.getStudentById(studentId);
            model.deleteStudent(studentToDelete);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_NOT_FOUND);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, studentId));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteStudentCommand)) {
            return false;
        }

        DeleteStudentCommand otherDeleteCommand = (DeleteStudentCommand) other;
        return studentId.equals(otherDeleteCommand.studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .toString();
    }
}
