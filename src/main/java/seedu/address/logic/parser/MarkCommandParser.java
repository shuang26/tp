package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.STUDENT_ID_MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.ParserStudentUtil.parseStudentId;

import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentId;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    @Override
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        String id = argMultimap.getPreamble();

        try {
            StudentId studentId = parseStudentId(id);
            return new MarkCommand(studentId);
        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage(), MarkCommand.MESSAGE_USAGE));
        }
    }

}
