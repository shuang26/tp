package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains tests for
 * {@code UnmarkCommand}.
 */
public class UnmarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final String testId = "A01A";

        assertCommandFailure(new UnmarkCommand(testId), model,
                "Requested student was not found in the student list.");
    }

    @Test
    public void equals() {
        final String testId = "A01A";
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
        assertFalse(standardCommand.equals(new UnmarkCommand("A01B")));

    }
}
