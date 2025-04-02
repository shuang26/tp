package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ATTENDANCE_UNMARKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook_OnePresent;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.StudentId;

/**
 * Contains tests for
 * {@code UnmarkCommand}.
 */
public class UnmarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook_OnePresent(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_studentIdNotFound_throwsCommandException() {
        final StudentId testId = new StudentId("A01A");

        assertCommandFailure(new UnmarkCommand(testId), model,
                "Requested student was not found in the student list.");
    }

    @Test
    public void execute_studentIdNull_throwsCommandException() {
        assertThrows(NullPointerException.class, null, () -> new UnmarkCommand(null).execute(model));
    }

    @Test
    public void execute_modelNull_throwsCommandException() {
        assertThrows(NullPointerException.class, null, () -> new UnmarkCommand(new StudentId("A01A")).execute(null));
    }

    @Test
    public void execute_studentIdFound_success() {
        final StudentId testId = model.getFilteredStudentList().get(0).getStudentId();

        String expectedMessage = String.format(MESSAGE_STUDENT_ATTENDANCE_UNMARKED, testId);

        assertCommandSuccess(new UnmarkCommand(testId), model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final StudentId testId = new StudentId("A01A");
        final UnmarkCommand standardCommand = new UnmarkCommand(testId);

        // same value -> returns true
        UnmarkCommand commandWithSameValue = new UnmarkCommand(testId);
        assertTrue(standardCommand.equals(commandWithSameValue));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different id -> returns false
        assertFalse(standardCommand.equals(new UnmarkCommand(new StudentId("A01B"))));

    }
}
