package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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

    @Test
    public void execute() {
        MarkAllCommand markAllCommand = new MarkAllCommand();
        List<Student> students = model.getFilteredStudentList();

        for (Student student : students) {
            student.setPresent();
        }

        assertCommandFailure(markAllCommand, model, "No students found in the student list.");
    }
}
