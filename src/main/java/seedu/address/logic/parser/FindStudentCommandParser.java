package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserStudentUtil.parseStudentId;

import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentId;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindStudentCommandParser implements Parser<FindStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentCommand
     * and returns a FindStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        String id = argMultimap.getPreamble();

        try {
            StudentId studentId = parseStudentId(id);
            return new FindStudentCommand(studentId);
        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage(), FindStudentCommand.MESSAGE_USAGE));
        }
    }
}
