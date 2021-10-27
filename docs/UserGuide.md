---
layout: page
title: User Guide
---

UNIon is a desktop app for organizing various types of contacts, optimized for use for the vast majority of computing students.
If you are already familiar with Unix commands, then UNIon will be easy for you to use.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `union.jar` from [here](https://github.com/AY2122S1-CS2103-T16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`ls -contacts`**: Lists all contacts.

   * **`touch`**`-n John Doe -p 98765432 -e johnd@example.com -a John street, block 123, #01-01`: Adds a contact named `John Doe` to the Address Book.

   * **`rm`**`3`: Deletes the 3rd contact shown in the current list.

   * **`rm -contacts`**: Deletes all contacts.

   * **`exit`**: Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `touch -n NAME`, `NAME` is a parameter which can be used as `touch -n John Doe`.

* Items in square brackets are optional.<br>
  e.g `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[-t TAG]…​` can be used as ` ` (i.e. 0 times), `-t friend`, `-t friend -t family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -p PHONE_NUMBER`, `-p PHONE_NUMBER -n NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Managing people

#### Adding a person: `touch`

Adds a person to the address book.

Format: `touch -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS [-t TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `touch -n John Doe -p 98765432 -e johnd@example.com -a John street, block 123, #01-01`
* `touch -n Betsy Crowe -t friend -e betsycrowe@example.com -a Newgate Prison -p 1234567 -t criminal`

#### Listing all persons: `ls -contacts`

Shows a list of all persons in the address book.

Format: `ls -contacts`

#### Editing a person: `vim`

Edits an existing person in the address book.

Format: `vim INDEX [-n NAME] [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e touching of tags is not cumulative.
* You can remove all the person’s tags by typing `-t` without specifying any tags after it.

Examples:
*  `vim 1 -p 91234567 -e johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `vim 2 -n Betsy Crower -t ` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Deleting a person: `rm`

Deletes the specified person from the address book.

Format: `rm INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `ls -contacts` followed by `rm 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `rm 1` deletes the 1st person in the results of the `find` command.

#### Clearing all people: `rm -contacts`

Clears all contacts from the address book.

Format: `rm -contacts`

### Managing folders

#### Adding a folder: `mkdir`

Creates a folder for contacts to be added into.

Format: `mkdir FOLDER_NAME`

* Creates a folder with the name `FOLDER_NAME`.

Examples:

* `mkdir CS2103` creates a folder with the name `CS2103`.

#### Adding contacts to a folder: `echo`

To organize and group contacts into an arbitrary folder
Format: `echo CONTACT_INDEX >> FOLDER_NAME`
- `CONTACT_INDEX` must be a positive integer 1, 2, 3, ...
- `FOLDER_NAME` must be an existing folder
    - Command fails if there is no existing folder with that name

#### Listing all folders: `ls -folders`

Retrieve list of all folders created

Format: `ls -folders`

#### Editing a folder name: `mv`

Replaces the old folder name with the new folder name

Format: `mv OLD_FOLDER_NAME` &#124; `NEW_FOLDER_NAME`

#### Locating folders by name: `find -folders`

Finds folders whose names contain any of the given keywords.

Format: `find -folders KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `cs2103` will match `CS2103`
* The order of the keywords does not matter. e.g. `Team Project CS2103` will match `CS2103 Team Project`
* Only the folder name is searched.
* Partial words will be matched e.g. `CS` will match `CS2103` and `CS2101`.
* Folders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `CS2103 Team Project` will return `CS2103`, `Team Project`

Examples:
* `find -folders CS` returns `CS2103` and `CS2101`
* `find -folders CS2103 Team Project` returns `CS2103`, `Team Project`

#### Deleting a folder: `rmdir`

Deletes a specified folder

Format `rmdir FOLDER_NAME`

* Deletes folder with the name `FOLDER_NAME`.

Examples:

* `rmdir CS1010` deletes a folder with the name `CS1010`.

#### Clearing all folders: `rm -folders`

Clears all folders from the address book.

Format: `rm -folders`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add new contact** | `touch -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS [-t TAG]` <br> e.g., `touch -n James Ho -p 22224444 -e jamesho@example.com -a 123, Clementi Rd, 1234665 -t friend -t colleague`
**List contacts** | `ls -contacts`
**Edit contact** | `vim INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]` <br> e.g., `vim 2 -n James Lee -e jameslee@example.com`
**Find contact** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Delete contact** | `rm INDEX`<br> e.g., `rm 3`
**Clear contacts** | `rm -contacts`
**Add new folder** | `mkdir FOLDER_NAME` <br> e.g. `mkdir CS2103`
**Add contact to folder** | `echo INDEX >> FOLDER_NAME` <br> e.g., `echo 3 >> CS2103`
**List folders** | `ls -folders`
**Edit folder name** | `mv OLD_FOLDER_NAME` &#124; `NEW_FOLDER_NAME` <br> e.g., `mv CS2103` &#124; `CS2102`
**Find folders** | `find -folders KEYWORD [MORE_KEYWORDS]`<br> e.g., `find -folders CS2103`
**Delete folder** | `rmdir FOLDER_NAME` <br> e.g., `rmdir CS1010`
**Clear folders** | `rm -folders`
