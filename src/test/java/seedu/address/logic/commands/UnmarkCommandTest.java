package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.UnmarkCommand.MESSAGE_NOT_IMPLEMENTED_YET;
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
        final String test_id = "A01A";

        assertCommandFailure(new UnmarkCommand(test_id), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }

    @Test
    public void equals() {
        final String test_id = "A01A";
        final UnmarkCommand standardCommand = new UnmarkCommand(test_id);

        // same value -> returns true
        UnmarkCommand commandWithSameValue = new UnmarkCommand(test_id);
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
