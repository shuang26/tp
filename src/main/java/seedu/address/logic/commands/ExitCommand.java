package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
