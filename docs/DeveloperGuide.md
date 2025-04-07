---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# CareBook Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

Some of CareBook features are adapted from the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
As such, CareBook contains some of the code and documentation from AddressBook-Level3. 

The list of third-party libraries used are: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](hhttps://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete A10A`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete A10A")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete A10A` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteStudentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteStudentCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteStudentCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddStudentCommandParser`, `DeleteStudentCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the CareBook data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both CareBook data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add a Student
Logic Classes:
* <code>AddStudentCommand.java</code>
* <code>AddStudentCommandParser.java</code>

<code>AddStudentCommandParser</code> parses the input entered by user before executing <code>AddStudentCommand</code> to add a student to <code>Model</code>

This is illustrated in the activity diagram below:

<puml src="diagrams/AddStudentCommand.puml" width="600"/>

* isValidPhone ensures that phone number is exactly 8 digits and begin with either 6, 8 or 9.
* isValidEmail ensures that email has the @ domain.
* isUniqueId ensures that <StudentId> is unique.
* isValidParentName and isValidStudentName both ensure that they only contain alphanumeric characters

If all the above conditions are satisfied, the student is added to the model.

### Export Student Records
Logic Classes:
* <code>ExportCommand.java</code>
* <code>ExportCommandParser.java</code>

<code>ExportCommandParser</code> parses the input entered by user before executing <code>ExportCommand</code> to export
student name, parent name, student ID, parent email, parent phone number and attendance history of all students in <code>Model</cod>.

This is illustrated in the activity diagram below:

<puml src="diagrams/ExportCommand.puml" width="600"/>

* isValidFileName ensures that fileName only contains alphanumeric characters or underscore.
* isValidFileLength ensures that fileName does not exceed 100 characters.

If all the above conditions are satisfied, export command will be successfully executed and a .csv file will be created in directory where carebook.jar file is placed.

### Delete a Student 
Logic Classes:
* <code>DeleteStudentCommand.java</code>
* <code>DeleteStudentCommandParser.java</code>

<code>DeleteStudentCommandParser</code> parses the input entered by user before executing <code>DeleteStudentCommand</code> to 
delete a student from <code>Model</code>

<puml src="diagrams/DeleteStudentCommand.puml" width="600"/>

* isValidId ensures that the studentId entered already exists in CareBook

If the above condition is satisfied, delete student command will be successfully executed.

### Find a Student
Logic classes:
* <code>FindStudentCommand.java</code>
* <code>FindStudentCommandParser.java</code>

<puml src="diagrams/FindStudentCommand.puml" width="600"/>

* isValidId ensures that the studentId entered already exists in CareBook

If the above condition is satisfied, find student command will be successfully executed.

<code>FindStudentCommandParser</code> parses the input entered by user before executing <code>FindStudentCommand</code> to
find a student from <code>Model</code>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Daycare teacher managing daily rosters of 30 to 40 students (aged 1 to 12)
* Prefers desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**: CareBook helps daycare teachers manage classroom and parent communication by providing instant CLI access to students’ and parents' contacts and streamlining repetitive tasks like daily attendance and maintaining student records.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                              | I want to …​                                                                               | So that I can…​                                           |
|----------|--------------------------------------|--------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| `* * *`  | potential user exploring the product | see the instruction guide                                                                  | easily know how the app works                             |
| `* * *`  | daycare teacher                      | view the students' and parents' contacts                                                   | disseminate information to them                           |
| `* * *`  | daycare teacher                      | add student details to the system                                                          | keep records up to date as children enrol or leave        |
| `* * *`  | daycare teacher                      | remove student details from the system                                                     | keep records up to date as children enrol or leave        |
| `* * *`  | daycare teacher                      | mark students as present                                                                   | efficiently take attendance during busy mornings          |
| `* * *`  | daycare teacher                      | quickly search for a child's emergency contact details                                     | immediately inform parents in case of emergencies         |
| `* *`    | forgetful teacher                    | search for contacts by phone number                                                        | quickly identify who is calling/who to call               |
| `* *`    | daycare teacher                      | generate a summary of daily/monthly attendance                                             | track attendance accurately                               |
| `* *`    | new user                             | copy-paste the parents' contacts from the app                                              | easily send an email to all parents                       |
| `* *`    | forgetful user                       | filter the students in my class by subjects                                                | plan the class for the day better                         |
| `* *`    | daycare teacher                      | view all classes scheduled for the day                                                     | prepare for my daily routine                              |
| `* *`    | daycare teacher                      | make a note of my student (e.g., behaviour, meals, incidents)                              | keep track of important details                           |
| `* *`    | daycare teacher                      | edit student details from the system                                                       | keep records up to date as children enrol or leave        |
| `* *`    | daycare teacher                      | export student details                                                                     | transfer student details to other systems                 |
| `* *`    | daycare teacher                      | export parent details                                                                      | transfer parent details to other systems                  |
| `* *`    | daycare teacher                      | filter students by age group                                                               | access information for age-based activities               |
| `* *`    | daycare teacher                      | create a custom group for students                                                         | organise activities for special occasions                 |
| `* *`    | daycare teacher                      | flag important information (e.g. medical conditions/allergies) about a student             | quickly access important details in times of crisis       |
| `* *`    | daycare teacher                      | access an audit log of student information updates                                         | track when and what information was modified              |
| `* *`    | daycare teacher                      | record students' absence with their reasons                                                | maintain accurate records and organise makeup lessons     |
| `* *`    | daycare teacher                      | see which students share the same emergency contact                                        | coordinate communication for siblings in the class        |
| `* *`    | daycare teacher                      | view basic student information (age, name, class) without accessing their complete records | quickly identify students                                 |
| `* *`    | daycare teacher                      | receive alerts when a student's information is updated                                     | stay informed about important changes                     |
| `* *`    | daycare teacher                      | undo previous commands                                                                     | revert any errors                                         |
| `*`      | daycare teacher                      | schedule and send pre-set messages to parents                                              | remind them about upcoming events or tasks effortlessly   |
| `*`      | daycare teacher                      | receive automated alerts if a child is absent for multiple days                            | follow up with parents if needed                          |
| `*`      | daycare teacher                      | set reminders for student-specific events (e.g., medication time)                          | avoid missing important occasions                         |
| `*`      | expert user                          | sort students' records by various criteria (e.g., name)                                    | find information more efficiently                         |
| `*`      | forgetful user                       | set a preferred name for each student                                                      | remember their names and address them properly            |
| `*`      | daycare teacher                      | see attendance trends over a certain period                                                | identify students who have been missing classes           |
| `*`      | daycare teacher                      | print a list of students with their emergency contacts as a PDF                            | have a copy for quick reference in case of a power outage |

### Use cases
(For all use cases below, the **System** is the `CareBook` and the **Actor** is the `Daycare Teacher`, unless specified otherwise.)<br>

#### Use case: UC1 - Add a New Student
**MSS**
1. User requests to add a student.
2. CareBook validates student details (student name, parent name, student ID, phone number, email address and address).
3. CareBook adds the new student.
4. CareBook confirms successful addition.

   Use case ends.

**Extensions**
* 2a. CareBook detects an invalid  student name, parent name, student ID, phone number, email address, or address.
    * 2a1. CareBook displays an error message.

      Use case resumes from step 1.

* 2b. CareBook detects a duplicate student ID.
    * 2b1. CareBook displays an error message.

      Use case resumes from step 1.

* 2c. CareBook detects an incorrect command format or unknown command.
    * 2c1. CareBook displays an error message.

      Use case resumes from step 1.

#### Use case: UC2 - Edit a Student
**MSS**
1. User requests to edit a student with new details by using student ID.
2. CareBook verifies that the student exists.
3. CareBook updates the student with the new details.
4. CareBook confirms successful edit.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC3 - Delete a Student

**MSS**

1. User requests to delete a student by student ID.
2. CareBook verifies that the student exists.
3. CareBook removes the student.
4. CareBook confirms successful removal.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC4 - View All Students

**MSS**

1. User requests to list all students.
2. CareBook displays a list of all students.

   Use case ends.

**Extensions**

* 2a. CareBook detects an empty list of students.

    * 2a1. CareBook displays an error message.

      Use case ends.

#### Use case: UC5 - Find a Student by Student ID

**MSS**

1. User requests to find a student by student ID.
2. CareBook verifies that the student exists.
3. CareBook retrieves the student’s details.
4. CareBook displays the student’s information.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC6 - Mark a Student Attendance as Present

**MSS**

1. User requests to mark a student as present by student ID.
2. CareBook verifies that the student exists.
3. CareBook updates the attendance record for that student.
4. CareBook confirms that the student has been marked present.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID, is unable to find student ID, or finds that the student is already marked as present.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC7 - Mark a Student Attendance as Absent

**MSS**

1. User requests to mark a student as absent by student ID.
2. CareBook verifies that the student exists.
3. CareBook updates the attendance record for that student.
4. CareBook confirms that the student has been marked absent.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID, is unable to find student ID, or finds the that student is already marked as absent.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC8 - Clear All Students

**MSS**

1. User requests to clear all students.
2. CareBook clears all students.

   Use case ends.

#### Use case: UC9 - View the Instruction Guide

**MSS**

1. User requests for help.
2. CareBook displays a list of all commands, their respective parameters, and an example of usage.

   Use case ends.

#### Use case: UC10 - Mark All Students Attendance as Present

**MSS**

1. User requests to mark all students as present.
2. CareBook updates the attendance record for every student as present.
3. CareBook confirms that all students have been marked present.

   Use case ends.

**Extensions**

* 2a. CareBook detects an empty list of students.

    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC11 - Mark All Students Attendance as Absent

**MSS**

1. User requests to mark all students as absent.

2. CareBook updates the attendance record for every student as absent.

3. CareBook confirms that all students have been marked absent.

   Use case ends.

**Extensions**

* 2a. CareBook detects an empty list of students.

    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC12 - Export All Students Information

**MSS**

1. User requests to export all students information with a filename.

2. CareBook saves all students information into the directory where the CareBook application is located.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid filename.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an empty list of students.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.


### Non-Functional Requirements
1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 50 students without a noticeable sluggishness in performance for typical usage.
3.  System does not take more than 1s to load all data from files when launching.
4.  All commands execute in less than 1s, including saving of data to files.
5.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
6.  Commands should be intuitive and easy to remember.
7.  Data should be saved after each change-causing command.
8.  Error messages should offer support to user (help user get the right commands).
9.  The app should not require complex installation steps.
10. It should be a lightweight application (<100MB) and not require additional dependencies beyond Java.

### Glossary
* **Command**: An instruction entered by the Daycare Teacher into the CareBook system to perform a specific action (e.g., "add", "delete", "find", "list", "mark", "help").
* **Command Format**: The required structure and syntax for a command, including any necessary parameters (e.g., "add <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>" for adding a student).
* **Daycare Teacher**: The primary user of the CareBook application, responsible for managing student records and attendance.
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Student Record**: A set of data representing a student's personal information, including details like student ID, name, parent contact, and attendance.
* **Validation**: The system’s process of checking that entered data (e.g., Student ID, ParentPhoneNumber) meets predefined rules before accepting it.
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

#### Launch and shutdown

1. **Initial launch**
   1. Download the `CareBook.jar` file and copy into an empty folder<br><br>
   1. Ensure you have `Java 17` or above installed in your computer.<br><br>
   1. Open a command terminal, `cd` into the folder you placed your .jar file and type `java -jar CareBook.jar` and press enter to run CareBook application.<br>
      **Expected:** 
      * Shows the GUI with a set of sample students. 
      * The window size may not be optimum.<br><br>
    
1. **Saving window preferences**
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.<br><br>
   1. Re-launch the app by typing `java -jar CareBook.jar` and pressing enter.<br>
      **Expected**:
      * The most recent window size and location is retained.<br><br>

1. **Verifying Logs during launch**
   1. Launch the app by typing `java -jar CareBook.jar` and pressing enter.<br><br>
   1. Observe the logs printed in the terminal during startup.<br>
      **Expected**:
      * Logs should be displayed with appropriate timestamps.
      * Warnings about JavaFX configuration may appear but should not affect functionality.

#### Deleting a student

1. **Deleting a student while all students are being shown**
   1. Prerequisites: List all students using the `list` command. Multiple students in the list.<br><br>
   1. Test case: `delete A10A`<br>
      **Expected**: 
      * Student with ID A10A is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.<br><br>
   1. Test case: `delete A99Z`<br>
      **Expected:**
      * No student is deleted. Error details shown in the status message. Status bar remains the same.<br><br>
   1. Other incorrect delete commands to try: `delete`, `delete a10`, `...` (where student ID is invalid).<br>
      **Expected:**
      * Similar to previous.<br><br>

1. **Deleting a Student When No Students Are Displayed**
   1. Prerequisites: Clear the list using the `clear` command.<br><br>
   1. Test case: `delete A99Z`<br>
      **Expected:**
      * No student is deleted. Error details shown in the status message. Status bar remains the same.


#### Saving data

1. **Dealing with missing data files**
   1. Navigate to the application's `data` directory.<br><br>
   1. Delete or rename the data file (e.g., carebook.json). <br><br>
   1. Launch the app.
      **Expected:**
       * The application should create a new default data file if one does not exist.
       * The list of students should contain default sample student data.
       * A warning message (e.g., “Creating a new data file data\carebook.json populated with a sample CareBook.”) should be logged.<br><br>
   
1. **Dealing with corrupted data files**
   1. Navigate to the application's `data` directory and open `carebook.json` in a text editor.<br><br>
   1. Modify the content to be invalid JSON (e.g., remove a closing brace or change a key name to an invalid format).<br><br>
   1. Save the file and launch the app.
      **Expected:**
       * The application should detect the corruption and handle it.
       * A warning message (e.g., "WARNING: Error reading from jsonFile file data\carebook.json: ...") should be logged.
       * The app will launch with an empty student list.
       * The application should not crash and should remain functional.<br><br>

1. **Ensuring data is saved after adding/deleting a student**
   1. Launch the app and modify the data by adding/deleting a student.<br><br>
   1. Close the application and re-open it.
      **Expected:**
       * The added/deleted student should appear/not appear in the list.

## **Appendix: Effort**
1. **Difficulty Level and Challenges Faced**

   Our project builds on AddressBook Level 3 (AB3) but required significant refactoring to better fit our target users' needs. As a result, the complexity of data management, validation, and UI representation increased.<br><br>
    **Key challenges included:**
    * Extensive refactoring: From `person` model to `student` model.
    * Editing and updating test cases: Ensuring existing test cases remained valid after structural changes while writing new ones for added functionalities.
    * Maintaining code consistency: Ensuring that refactored components integrated smoothly without introducing regressions.<br><br>
    
1. **Effort Required**

   The project effort was distributed across key areas:<br>
   * Feature Development (45%) – Modifying existing commands and implementing new commands (e.g. mark, markall, export).
   * Testing & Debugging (30%) – Updating test cases, refactoring test structure, and ensuring correctness after modifications.
   * Data Management & Storage (25%) – Modifying JSON storage to handle new fields in `Student` model.<br><br>
    
1. **Achievements**
    * Successfully modified AB3 to align with our target users' needs while maintaining its core functionality.
    * Refactored the codebase effectively, improving maintainability and extensibility.

## **Appendix: Planned Enhancements**
Team members (count): 5

1. **Improve UI (Main Window and Find Window) for long names and addresses**: When dealing with long name and addresses, CareBook truncates these values in both the main and find window. 
Furthermore, find window cannot be resized. To improve this in the future, we would make the UI display long name and address values and make find window 
resizable.

2. **Modify edit command to detect changes**: When editing student contact, CareBook does not recognise that no changes are made and edit command is allowed
to go through without throwing an error. For future improvements, we would make changes such that edit command will detect no changes are being made
and throw an error.

3. **Modify UI to display student name and studentId in sorted order**: When adding new students, student that has just been added
will appear at the bottom of the UI display. This makes it hard for users to track their students. For future improvements,
we would implement a comparator class to sort students by their student ID.

4. **Modify export command to export student records to different file types and different directories**: When exporting student records,
the exported file is automatically saved as a .csv file to the current directory of carebook.jar file. When user 
tries to copy and paste records from .csv file to file types like excel, there is a slight formatting issue. For future improvements,
we would implement a method to allow users to export to a different file type and directory of their choice.

5. **Modify add command to allow names with special characters**: Currently, users can only add student and parent names that 
consist of alphanumeric characters and space. For future improvements, we plan to allow users to add names with special characters like
@, / or names in different languages (e.g. Arabic, Chinese).

6. **Modify add command to validate that email contains a period**: Currently, users can enter emails that do not contain 
a period in the domain (e.g. a@bc). For future improvements, we plan to enhance email validation by ensuring that the 
domain part of the email contains at least one period (e.g. example.com) to better reflect real-world email formats.

7. **Automatically refresh attendance records on a new date**: Currently, users need to trigger a refresh to update the attendance records on a new date.
When a user is working on this app at late night, past 12am, the date will change, hence they will have to manually refresh the data using commands like `list`.
For further improvement, we plan to automatically detect a date change while the app is running, and refresh the attendance records without user-triggered actions.
