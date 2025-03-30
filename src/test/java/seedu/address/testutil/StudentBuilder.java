package seedu.address.testutil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Address;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Amy Cena";
    public static final String DEFAULT_PARENT_NAME = "Peter Lee";
    public static final String DEFAULT_STUDENT_ID = "A98Z";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Set<LocalDate> DEFAULT_ATTENDANCE = new HashSet<>();

    private Name studentName;
    private Name parentName;
    private StudentId studentId;
    private Phone phone;
    private Email email;
    private Address address;
    private Attendance attendance;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new Name(DEFAULT_STUDENT_NAME);
        parentName = new Name(DEFAULT_PARENT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        attendance = new Attendance(DEFAULT_ATTENDANCE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getStudentName();
        parentName = studentToCopy.getParentName();
        studentId = studentToCopy.getStudentId();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        attendance = studentToCopy.getAttendance();
    }

    /**
     * Sets the {@code Student Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withStudentName(String name) {
        this.studentName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Parent Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withParentName(String name) {
        this.parentName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Parent Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withSId(String id) {
        this.studentId = new StudentId(id);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withAttendance(Set<LocalDate> attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    public Student build() {
        return new Student(studentName, studentId, parentName, phone, email, address, new Attendance(new HashSet<>()));
    }

}
