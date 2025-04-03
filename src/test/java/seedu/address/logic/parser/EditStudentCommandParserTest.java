package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentId;

public class EditStudentCommandParserTest {
    private final EditStudentCommandParser parser = new EditStudentCommandParser();

    @Test
    public void parse_validArgs_returnsEditStudentCommand() throws Exception {
        String input = VALID_STUDENT_ID_AMY + STUDENT_NAME_DESC_AMY;
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentCommand.EditStudentDescriptor();
        descriptor.setStudentName(new Name(VALID_STUDENT_NAME_AMY));

        EditStudentCommand expectedCommand = new EditStudentCommand(new StudentId(VALID_STUDENT_ID_AMY), descriptor);
        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_missingStudentId_throwsParseException() {
        // Missing student ID
        String input = STUDENT_NAME_DESC_AMY;
        assertThrows(ParseException.class, () -> parser.parse(input),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingOptionalFields_returnsEditStudentCommand() throws Exception {
        String input = VALID_STUDENT_ID_AMY + STUDENT_NAME_DESC_AMY;
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentCommand.EditStudentDescriptor();
        descriptor.setStudentName(new Name(VALID_STUDENT_NAME_AMY));

        EditStudentCommand expectedCommand = new EditStudentCommand(new StudentId(VALID_STUDENT_ID_AMY), descriptor);
        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        String input = "i/invalid-id" + STUDENT_NAME_DESC_AMY; // Invalid student ID format
        assertThrows(ParseException.class, () -> parser.parse(input),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE));
    }
}
