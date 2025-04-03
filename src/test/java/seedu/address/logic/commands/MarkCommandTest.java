package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_NOT_FOUND;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ATTENDANCE_MARKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook_onePresent;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.StudentId;


/**
 * Contains tests for
 * {@code MarkCommand}.
 */
public class MarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook_onePresent(), new UserPrefs());

    @Test
    public void execute_studentIdNotFound_throwsCommandException() {
        final StudentId testId = new StudentId("A01A");

        assertCommandFailure(new MarkCommand(testId), model, MESSAGE_INVALID_STUDENT_NOT_FOUND);
    }

    @Test
    public void execute_studentIdNull_throwsCommandException() {
        assertThrows(NullPointerException.class, null, () -> new MarkCommand(null).execute(model));
    }

    @Test
    public void execute_modelNull_throwsCommandException() {
        assertThrows(NullPointerException.class, null, () -> new MarkCommand(new StudentId("A01A")).execute(null));
    }

    @Test
    public void execute_studentIdFound_success() {
        final StudentId testId = model.getFilteredStudentList().get(0).getStudentId();

        String expectedMessage = String.format(MESSAGE_STUDENT_ATTENDANCE_MARKED, testId);

        assertCommandSuccess(new MarkCommand(testId), model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final StudentId testId = new StudentId("A01A");
        final MarkCommand standardCommand = new MarkCommand(testId);

        // same value -> returns true
        MarkCommand commandWithSameValue = new MarkCommand(testId);
        assertTrue(standardCommand.equals(commandWithSameValue));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different id -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(new StudentId("A01B"))));

    }
}
