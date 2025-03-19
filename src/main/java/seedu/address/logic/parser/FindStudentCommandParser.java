package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdEqualsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentCommand.MESSAGE_USAGE));
        }

        try {
            StudentId studentId = new StudentId(trimmedArgs);
            return new FindStudentCommand(new StudentIdEqualsPredicate(studentId));
        } catch (IllegalArgumentException e) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS + "\n" + FindStudentCommand.MESSAGE_USAGE);
        }
    }

}
