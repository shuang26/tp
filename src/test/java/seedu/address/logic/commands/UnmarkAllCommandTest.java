package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

public class UnmarkAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_unmarkall_success() {
        UnmarkAllCommand unmarkAllCommand = new UnmarkAllCommand();
        List<Student> students = model.getFilteredStudentList();

        for (Student student : students) {
            student.setPresent();
        }

        assertCommandSuccess(unmarkAllCommand, model, MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED, expectedModel);
    }

    @Test
    public void execute_emptyStudentList_throwsCommandException() {
        // Create a model with an empty student list
        UnmarkAllCommand unmarkAllCommand = new UnmarkAllCommand();

        model = new ModelManager();
        assertCommandFailure(unmarkAllCommand, model, UnmarkAllCommand.MESSAGE_EMPTY_STUDENT_LIST);
    }
}

