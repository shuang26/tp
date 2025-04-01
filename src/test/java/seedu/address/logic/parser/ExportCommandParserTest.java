package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

/**
 * Contains tests for
 * {@code ExportCommandParser}.
 */
public class ExportCommandParserTest {

    private final ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgs_returnsExportCommand() throws Exception {
        String validFileName = "attendance_summary_sheet";
        File expectedFile = ParserStudentUtil.parseFileName(validFileName);
        ExportCommand expectedCommand = new ExportCommand(expectedFile);
        assertParseSuccess(parser, validFileName, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String emptyParam = "";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE);
        assertParseFailure(parser, emptyParam, expectedMessage);
    }

    @Test
    public void parse_invalidFileNameInvalidArgs_throwsParseException() {
        String invalidFileName = "student/records";
        assertParseFailure(parser, invalidFileName, ExportCommandParser.MESSAGE_INVALID_FILE_NAME);
    }
}
