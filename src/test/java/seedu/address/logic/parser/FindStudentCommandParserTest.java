package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentIdEqualsPredicate;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindStudentCommandParserTest {

    private FindStudentCommandParser parser = new FindStudentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIdFormat_throwsParseException() {
        String studentId = "z99";
        assertParseFailure(parser, studentId,
                StudentId.MESSAGE_CONSTRAINTS + "\n" + FindStudentCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validFormat_returns_FindStudentCommand() {
        String studentId = "A01A";
        StudentId sId = new StudentId(studentId);
        assertParseSuccess(parser, studentId, new FindStudentCommand(new StudentIdEqualsPredicate(sId)));
    }
}
