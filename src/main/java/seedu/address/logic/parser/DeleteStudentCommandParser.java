package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserStudentUtil.parseStudentId;

import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentId;

/**
 * Parses input arguments and creates a new DeleteStudentCommand object.
 */
public class DeleteStudentCommandParser implements Parser<DeleteStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentCommand
     * and returns a DeleteStudentCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        String id = argMultimap.getPreamble();

        try {
            StudentId studentId = parseStudentId(id);
            return new DeleteStudentCommand(studentId);
        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage(), DeleteStudentCommand.MESSAGE_USAGE));
        }
    }
}
