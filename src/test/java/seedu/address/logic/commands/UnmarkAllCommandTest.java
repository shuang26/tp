package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED;
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
    private Model expectedModel = model;
    // edit expectedModel



    @Test
    public void execute() {
        UnmarkAllCommand unmarkAllCommand = new UnmarkAllCommand();
        List<Student> students = model.getFilteredStudentList();

        for (Student student : students) {
            student.setAbsent();
        }

        assertCommandSuccess(unmarkAllCommand, model, MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED, expectedModel);
    }
}

