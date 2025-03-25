package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

public class UnmarkAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute() {
        UnmarkAllCommand UnmarkAllCommand = new UnmarkAllCommand();
        List<Student> students = model.getFilteredStudentList();

        for (Student student : students) {
            student.setAbsent();
        }

        assertCommandFailure(UnmarkAllCommand, model, "No students found in the student list.");
    }
}

