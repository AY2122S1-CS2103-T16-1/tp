package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;

/**
 * Edits the name of an existing folder in the address book.
 */
public class EditFolderNameCommand extends Command {

    public static final String COMMAND_WORD = "mv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Renames the folder identified by the old "
            + "folder name used in the displayed folders list.\n"
            + "Parameters: OLD_FOLDER_NAME (Name must be an existing folder)"
            + " + NEW_FOLDER_NAME (Name must not already exist in folder list)\n"
            + "Example: " + COMMAND_WORD + " OLD_FOLDER_NAME | " + "NEW_FOLDER_NAME";

    public static final String MESSAGE_SUCCESS_EDIT_FOLDER_NAME = "Folder updated to: %1$s";
    public static final String MESSAGE_NO_SUCH_FOLDER = "No such folder found in UNIon to be renamed";
    public static final String MESSAGE_FOLDER_ALREADY_EXISTS = "Cannot rename folder to"
            + " an already existing folder in UNIon";

    private final Folder folderToRename;
    private final Folder folderToReplaceOld;

    /**
     * Creates a EditFolderNameCommand to update
     * the specified {@code folderToRename} to the
     * {@code folderToReplace} name.
     * @param folderToRename folder name to be changed.
     * @param folderToReplace new folder to replace old folder.
     */
    public EditFolderNameCommand(Folder folderToRename, Folder folderToReplace) {
        requireAllNonNull(folderToRename, folderToReplace);
        this.folderToRename = folderToRename;
        this.folderToReplaceOld = folderToReplace;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasFolder(folderToRename)) {
            throw new CommandException(MESSAGE_NO_SUCH_FOLDER);
        }
        if (model.hasFolder(folderToReplaceOld)) {
            throw new CommandException(MESSAGE_FOLDER_ALREADY_EXISTS);
        }

        model.setNewFolder(folderToRename, folderToReplaceOld);
        return new CommandResult(String.format(MESSAGE_SUCCESS_EDIT_FOLDER_NAME, folderToReplaceOld));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFolderNameCommand)) {
            return false;
        }

        // state check
        EditFolderNameCommand e = (EditFolderNameCommand) other;
        return this.folderToRename.equals(e.folderToRename)
                && this.folderToReplaceOld.equals(e.folderToReplaceOld);
    }


}
