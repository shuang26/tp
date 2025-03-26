package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

        if (id.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        }

        try {
            StudentId studentId = new StudentId(id);
            return new UnmarkCommand(studentId);
        } catch (IllegalArgumentException e) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS + "\n" + UnmarkCommand.MESSAGE_USAGE);
        }
    }

}
