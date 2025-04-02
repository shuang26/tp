package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_MIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TODAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withStudentName("Alice Pauline")
            .withParentName("Peter Lee")
            .withSId("A98L")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withAttendance(new HashSet<>()).build();
    public static final Student BENSON = new StudentBuilder().withStudentName("Benson Meier")
            .withParentName("Benny Meier")
            .withSId("A99Y")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAttendance(new HashSet<>(Collections.singleton(LocalDate.EPOCH))).build();
    public static final Student CARL = new StudentBuilder().withStudentName("Carl Kurz").withParentName("Clark Kurz")
            .withSId("A89Q")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withAttendance(new HashSet<>(Collections.singleton(LocalDate.now()))).build();
    public static final Student DANIEL = new StudentBuilder().withStudentName("Daniel Meier")
            .withParentName("Danielle Lee")
            .withSId("A79Z")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withAttendance(new HashSet<>(Collections.singleton(LocalDate.MIN))).build();
    public static final Student ELLE = new StudentBuilder().withStudentName("Elle Meyer").withParentName("Evan Meyer")
            .withSId("A97Z")
            .withPhone("94822244")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Student FIONA = new StudentBuilder().withStudentName("Fiona Kunz").withParentName("Frank Lee")
            .withSId("A99W")
            .withPhone("94824274")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withStudentName("George Best")
            .withParentName("Gerogia Bester")
            .withSId("A99L")
            .withPhone("94824424")
            .withEmail("anna@example.com").withAddress("4th street")
            .withAttendance(new HashSet<>()).build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withStudentName("Hoon Meier").withParentName("Hen Lee")
            .withSId("A90Z")
            .withPhone("84824244")
            .withEmail("stefan@example.com").withAddress("little india")
            .withAttendance(new HashSet<>(Collections.singleton(LocalDate.now()))).build();
    public static final Student IDA = new StudentBuilder().withStudentName("Ida Mueller").withParentName("Ilya Mueller")
            .withSId("A99M")
            .withPhone("84821314")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withAttendance(new HashSet<>(Collections.singleton(LocalDate.EPOCH))).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withStudentName(VALID_STUDENT_NAME_AMY)
            .withParentName("Amy Jackson")
            .withSId("A01A").withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withAttendance(VALID_ATTENDANCE_TODAY).build();
    public static final Student BOB = new StudentBuilder().withStudentName(VALID_STUDENT_NAME_BOB)
            .withParentName("Bob Marley")
            .withSId("A02A").withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withAttendance(VALID_ATTENDANCE_MIN)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
