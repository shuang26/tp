package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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

    @Test
    public void execute() {
        final StudentId testId = new StudentId("A01A");

        assertCommandFailure(new MarkCommand(testId), model, "Requested student was not found in the student list.");
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
