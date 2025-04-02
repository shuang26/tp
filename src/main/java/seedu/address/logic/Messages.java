package seedu.address.logic;

import static seedu.address.model.student.Email.SPECIAL_CHARACTERS;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.student.Student;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command. Please type \"help\" to check out "
            + "the available commands";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_ID = "The student ID provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d student listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_STUDENT_NOT_FOUND = "Student with this ID not found";
    public static final String MESSAGE_STUDENT_ATTENDANCE_MARKED = "Student %1$s marked as present!";
    public static final String MESSAGE_STUDENT_ATTENDANCE_UNMARKED = "Student %1$s marked as absent!";

    public static final String MESSAGE_ALL_STUDENT_ATTENDANCE_UNMARKED = "All students marked as absent!";
    public static final String MESSAGE_ALL_STUDENT_ATTENDANCE_MARKED = "All students marked as present!";


    public static final String ADDRESS_MESSAGE_CONSTRAINTS =
            "Addresses can take any values, and it should not be blank";
    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String STUDENT_ID_MESSAGE_CONSTRAINTS =
            "Student ID should be 4 characters beginning with A followed by 2 digits "
                    + "and ending with an alphabet in UPPERCASE";
    public static final String PHONE_MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and should be between 80000000 and 99999999";
    public static final String EMAIL_MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code student} for display to the user.
     */
    public static String format(Student student) {
        final StringBuilder builder = new StringBuilder();
        builder.append(student.getStudentName())
                .append("; Student Id: ")
                .append(student.getStudentId())
                .append("; Parent Name: ")
                .append(student.getParentName())
                .append("; Phone: ")
                .append(student.getPhone())
                .append("; Email: ")
                .append(student.getEmail())
                .append("; Address: ")
                .append(student.getAddress());
        return builder.toString();
    }

}
