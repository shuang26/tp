package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.EXPORT_FILE_HEADER;
import static seedu.address.logic.Messages.FILE_WRITE_ERROR;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_STUDENT_LIST;
import static seedu.address.logic.Messages.MESSAGE_EXPORT_SUCCESS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Exports the student ID, student name, parent name, parent email, parent number and attendance history
 * of students currently recorded in Care Book into a .csv file whose file name is specified by user
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the student ID, student name, parent name, parent email, parent number"
            + "\n attendance history of students to the current directory of where this app is saved as a .csv file.\n"
            + "Parameters: FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " attendance_summary";

    private final File fileName;

    public ExportCommand(File fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(fileName);
        ObservableList<Student> studentList = model.getAddressBook().getStudentList();

        if (studentList.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_STUDENT_LIST);
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(EXPORT_FILE_HEADER);
            for (Student student : studentList) {
                String studentId = student.getStudentId().toString();
                String studentName = student.getStudentName().toString();
                String parentName = student.getParentName().toString();
                String parentEmail = student.getEmail().toString();
                String phoneNumber = student.getPhone().toString();
                Set<LocalDate> attendanceDates = student.getAttendance().getAttendance();
                String attendanceToString = attendanceDates.stream()
                        .map(LocalDate::toString)
                        .collect(Collectors.joining("\n"));

                fileWriter.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        studentId, studentName, parentName, parentEmail, phoneNumber, attendanceToString));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new CommandException(FILE_WRITE_ERROR);
        }
        return new CommandResult(String.format(MESSAGE_EXPORT_SUCCESS, fileName.getAbsolutePath()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return fileName.equals(otherExportCommand.fileName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fileName", fileName)
                .toString();
    }
}
