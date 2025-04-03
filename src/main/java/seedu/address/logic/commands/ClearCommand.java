package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_CLEAR_SUCCESS;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the care book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_CLEAR_SUCCESS);
    }
}
