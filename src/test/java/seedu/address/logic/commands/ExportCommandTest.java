package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains tests for
 * {@code ExportCommand}.
 */
public class ExportCommandTest {

    @TempDir
    public Path tempFolder;

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validFileName_success() throws IOException {
        Path exportPath = tempFolder.resolve("attendance_summary.csv");
        File exportFile = exportPath.toFile();

        ExportCommand command = new ExportCommand(exportFile);
        String expectedMessage = ExportCommand.MESSAGE_SUCCESS + exportFile.getAbsolutePath();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertTrue(exportFile.exists());
        String exportedFile = Files.readString(exportPath);
        String expectedHeaders = "Student ID, Student Name, Parent Name, Parent's Email"
                + ", Parent's Number, Attendance History (Present Dates)";
        assertTrue(exportedFile.contains(expectedHeaders));
    }

    @Test
    public void execute_emptyStudentList_returnsMessageNoStudentFound() throws CommandException {
        Model emptyModel = new ModelManager();
        Path exportPath = tempFolder.resolve("attendance_summary.csv");
        File exportedFile = exportPath.toFile();
        ExportCommand command = new ExportCommand(exportedFile);

        assertCommandSuccess(command, emptyModel, ExportCommand.MESSAGE_NO_STUDENT_FOUND, emptyModel);
    }

    @Test
    public void execute_invalidFileName_throwsCommandException() {
        File invalidFileName = new File("../summary_sheet/attendance_sheet.csv");
        ExportCommand command = new ExportCommand(invalidFileName);

        assertCommandFailure(command, model, ExportCommand.FILE_WRITE_ERROR);
    }

    @Test
    public void equals() {
        File file1 = new File("summary_sheet_1.csv");
        File file2 = new File("summary_sheet_2.csv");
        ExportCommand command1 = new ExportCommand(file1);
        ExportCommand command2 = new ExportCommand(file2);
        ExportCommand command1Copy = new ExportCommand(file1);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same file name -> returns true
        assertTrue(command1.equals(command1Copy));

        // different objects -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different file names -> returns false
        assertFalse(command1.equals(command2));
    }

    @Test
    public void toStringMethod() {
        File file = new File("attendance_sheet.csv");
        ExportCommand command = new ExportCommand(file);
        String expectedMessage = ExportCommand.class.getCanonicalName() + "{fileName=" + file + "}";
        assertEquals(expectedMessage, command.toString());
    }
}
