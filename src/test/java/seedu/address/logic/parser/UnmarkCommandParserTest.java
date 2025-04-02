package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.student.StudentId.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.model.student.StudentId;

/**
 * Contains tests for
 * {@code UnmarkCommandParser}.
 */
public class UnmarkCommandParserTest {
    private final UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_idSpecified_success() {
        // have id

        StudentId studentId = new StudentId("A01A");
        assertParseSuccess(parser, studentId.toString(), new UnmarkCommand(studentId));
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);

    }

    @Test
    public void parse_invalidStudentID_failure() {
        String expectedMessage = MESSAGE_CONSTRAINTS + '\n' + UnmarkCommand.MESSAGE_USAGE;

        // no parameters
        assertParseFailure(parser, "A000", expectedMessage);

    }
}
