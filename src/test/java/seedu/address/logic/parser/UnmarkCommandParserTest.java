package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkCommand;

/**
 * Contains tests for
 * {@code UnmarkCommandParser}.
 */
public class UnmarkCommandParserTest {
    private final UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_idSpecified_success() {
        // have id

        String studentId = "A01A";
        assertParseSuccess(parser, studentId, new UnmarkCommand(studentId));
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);

    }
}
