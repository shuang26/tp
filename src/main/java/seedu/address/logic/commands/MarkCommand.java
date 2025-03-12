package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the student identified as present by the "
            + "student id.\nParameters: [STUDENT_ID]\n"
            + "Example: " + COMMAND_WORD + " A01A";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Mark command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Student ID: %1$s";

    private final String id;

    public MarkCommand(String id) {
        requireAllNonNull(id);

        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand e = (MarkCommand) other;
        return id.equals(e.id);
    }
}
