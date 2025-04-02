package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_studentIdNotFound_throwsCommandException() {
        StudentId nonExistentId = new StudentId("A99Z");
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(nonExistentId);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_STUDENT_ID_NOT_FOUND, nonExistentId);
        assertCommandFailure(deleteStudentCommand, model, expectedMessage);
    }

    @Test
    public void execute_deleteStudent_success() {
        Student studentToDelete = model.getFilteredStudentList().get(0);
        StudentId studentId = studentToDelete.getStudentId();
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(studentId);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(studentToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(new StudentId("A99Z"));
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(new StudentId("A98Z"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same student id -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(new StudentId("A99Z"));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student id -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        StudentId targetStudentId = new StudentId("A01A");
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(targetStudentId);
        String expected = DeleteStudentCommand.class.getCanonicalName()
                + "{targetStudentId=" + targetStudentId + "}";
        assertEquals(expected, deleteStudentCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(Student -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
