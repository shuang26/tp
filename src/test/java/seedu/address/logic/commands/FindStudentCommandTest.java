package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_FOUND;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ID_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentIdEqualsPredicate;

/**
 * Contains tests for
 * {@code FindStudentCommand}.
 */
public class FindStudentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentId firstPredicate =
                new StudentId("A01A");
        StudentId secondPredicate =
                new StudentId("A99B");

        FindStudentCommand findFirstCommand = new FindStudentCommand(firstPredicate);
        FindStudentCommand findSecondCommand = new FindStudentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStudentCommand findFirstCommandCopy = new FindStudentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // same string -> returns true
        assertTrue(findFirstCommand.equals(new FindStudentCommand(
                new StudentId("A01A"))));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENT_ID_NOT_FOUND, "A97B");
        FindStudentCommand command = new FindStudentCommand(new StudentId("A97B"));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENT_FOUND);
        StudentIdEqualsPredicate predicate = preparePredicate("A98L");
        FindStudentCommand command = new FindStudentCommand(new StudentId("A98L"));
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(1, model.getFilteredStudentList().size());
        assertEquals("A98L", model.getFilteredStudentList().get(0).getStudentId().toString());
    }

    @Test
    public void toStringMethod() {
        StudentIdEqualsPredicate predicate = preparePredicate("A01A");
        FindStudentCommand findStudentCommand = new FindStudentCommand(new StudentId("A01A"));
        String expected = FindStudentCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findStudentCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdEqualsPredicate}.
     */
    private StudentIdEqualsPredicate preparePredicate(String userInput) {
        return new StudentIdEqualsPredicate(new StudentId(userInput));
    }
}
