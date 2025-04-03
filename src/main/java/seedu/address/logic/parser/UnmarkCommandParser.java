package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserStudentUtil.parseStudentId;

import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentId;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {

    @Override
    public UnmarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        String id = argMultimap.getPreamble();

        try {
            StudentId studentId = parseStudentId(id);
            return new UnmarkCommand(studentId);
        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage(), UnmarkCommand.MESSAGE_USAGE));
        }
    }

}
