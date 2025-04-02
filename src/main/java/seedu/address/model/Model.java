package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The person must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setStudent(Student target, Student editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredStudentList();

    Student getStudentById(StudentId studentId, List<Student> studentList);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Checks if the filtered student list is empty.
     *
     * @return true if there are no students in the filtered list, false otherwise.
     */
    boolean isStudentListEmpty();

    /**
     * Marks all students in the filtered list as present and refreshes the list.
     * This method updates the attendance status of all students and then refreshes the filtered list.
     */
    void markAllStudents();

    /**
     * Marks all students in the filtered list as absent and refreshes the list.
     * This method updates the attendance status of all students and then refreshes the filtered list.
     */
    void unmarkAllStudents();

}
