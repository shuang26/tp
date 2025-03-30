package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.List;

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
    private Model expectedModel = model;
    // edit expectedModel

    @Test
    public void execute() {
        MarkAllCommand markAllCommand = new MarkAllCommand();
        List<Student> students = model.getFilteredStudentList();

        for (Student student : students) {
            student.setPresent();
        }

        assertCommandSuccess(markAllCommand, model, MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED, expectedModel);
    }
}
