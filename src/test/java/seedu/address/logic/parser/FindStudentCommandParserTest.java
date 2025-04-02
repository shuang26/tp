package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.STUDENT_ID_MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentIdEqualsPredicate;

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
        String studentId = "z99";
        assertParseFailure(parser, studentId,
                STUDENT_ID_MESSAGE_CONSTRAINTS + "\n" + FindStudentCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validFormat_success() {
        String studentId = "A01A";
        StudentId sId = new StudentId(studentId);
        assertParseSuccess(parser, studentId, new FindStudentCommand(new StudentIdEqualsPredicate(sId)));
    }
}
