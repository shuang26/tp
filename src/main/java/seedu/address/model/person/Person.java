package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Attendance;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name studentName;
    private final Name parentName;
    private final Phone phone;
    private final Email email;
    private final StudentId studentId;
    private Attendance attendance;
    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name studentName, Name parentName, StudentId studentId,
                  Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(studentName, parentName, studentId, phone, email, address, tags);
        this.studentName = studentName;
        this.parentName = parentName;
        this.studentId = studentId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getStudentName() {
        return studentName;
    }

    public Name getParentName() {
        return parentName;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isPresent() {
        return this.attendance.getIsPresentToday();
    }

    public void setPresent() {
        this.attendance.setPresent();
    }

    public void setAbsent() {
        this.attendance.setAbsent();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return studentName.equals(otherPerson.studentName)
                && parentName.equals(otherPerson.parentName)
                && studentId.equals(otherPerson.studentId)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, parentName, studentId, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", studentName)
                .add("parentName", parentName)
                .add("studentId", studentId)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
