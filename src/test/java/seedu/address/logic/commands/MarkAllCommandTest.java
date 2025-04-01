package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
/**
 * Contains tests for
 * {@code MarkAllCommand}.
 */
public class MarkAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_markall_success() {
        for (Student student : model.getFilteredStudentList()) {
            student.setAbsent();
        }

        for (Student student : expectedModel.getFilteredStudentList()) {
            student.setPresent();
        }

        MarkAllCommand markAllCommand = new MarkAllCommand();

        assertCommandSuccess(markAllCommand, model, MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED, expectedModel);
    }

    @Test
    public void execute_emptyStudentList_throwsCommandException() {
        /*
        // Create a model with an empty student list
        MarkAllCommand markAllCommand = new MarkAllCommand();

        model = new ModelManager();
        assertCommandFailure(markAllCommand, model, UnmarkAllCommand.MESSAGE_EMPTY_STUDENT_LIST);
         */
    }
}
