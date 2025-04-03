package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ID_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editStudentName_success() throws Exception {
        Student studentToEdit = model.getFilteredStudentList().get(0);
        StudentId studentId = studentToEdit.getStudentId();

        // Make sure the new details don't create a duplicate
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setStudentName(new Name("New Unique Name")); // Ensure uniqueness

        EditStudentCommand editStudentCommand = new EditStudentCommand(studentId, descriptor);
        CommandResult commandResult = editStudentCommand.execute(model);

        assertEquals("Edited student: New Unique Name; Student Id: A98L; Parent Name: Peter Lee; "
                        + "Phone: 94351253; Email: alice@example.com; Address: 123, Jurong West Ave 6, #08-111",
                commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_editStudentPhoneNumberOnly_success() throws CommandException {
        Student studentToEdit = model.getFilteredStudentList().get(0);
        StudentId studentId = studentToEdit.getStudentId();

        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setPhone(new Phone("99998888")); // Only updating phone

        EditStudentCommand editStudentCommand = new EditStudentCommand(studentId, descriptor);
        CommandResult commandResult = editStudentCommand.execute(model);

        assertTrue(commandResult.getFeedbackToUser().contains("99998888")); // Ensure phone was updated
    }

    @Test
    public void execute_noStudentFound_failure() {
        String expectedMessage = String.format(MESSAGE_STUDENT_ID_NOT_FOUND, "A97B");
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setStudentName(new Name("New Unique Name"));

        EditStudentCommand editStudentCommand = new EditStudentCommand(new StudentId("A97B"), descriptor);
        assertCommandFailure(editStudentCommand, model, expectedMessage);
    }

    @Test
    public void execute_editIDToSameAsAnotherStudent_failure() {
        Student studentToEdit = model.getFilteredStudentList().get(0);
        StudentId originalStudentId = studentToEdit.getStudentId();
        StudentId duplicateStudentId = model.getFilteredStudentList().get(1).getStudentId();

        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setStudentId(duplicateStudentId);

        EditStudentCommand editStudentCommand = new EditStudentCommand(originalStudentId, descriptor);

        try {
            CommandResult commandResult = editStudentCommand.execute(model);
            System.out.println(commandResult.getFeedbackToUser());
        } catch (CommandException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        assertCommandFailure(editStudentCommand, model, "This student already " +
                "exists in the address book.");
    }


    @Test
    public void equals() {
        final EditStudentCommand standardCommand = new EditStudentCommand(new StudentId("A98L"), DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditStudentCommand commandWithSameValues = new EditStudentCommand(new StudentId("A98L"), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(new StudentId("A99Y"), DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(new StudentId("A98L"), DESC_BOB)));
    }
}
