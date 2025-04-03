package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.model.student.StudentId;

/**
 * Contains tests for
 * {@code FindStudentCommandParser}.
 */
public class FindStudentCommandParserTest {

    private FindStudentCommandParser parser = new FindStudentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIdFormat_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_STUDENT_ID, FindStudentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "A00AB", expectedMessage);
    }

    @Test
    public void parse_validFormat_success() {
        String studentId = "A01A";
        StudentId sId = new StudentId(studentId);
        assertParseSuccess(parser, studentId, new FindStudentCommand(sId));
    }
}
