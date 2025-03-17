package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s student ID matches the provided student ID (no case sensitivity).
 */
public class StudentIdEqualsPredicate implements Predicate<Person> {
    private final StudentId studentId;

    public StudentIdEqualsPredicate(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean test(Person person) {
        return person.getStudentId().toString().equalsIgnoreCase(studentId.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StudentIdEqualsPredicate)) {
            return false;
        }
        StudentIdEqualsPredicate otherPredicate = (StudentIdEqualsPredicate) other;
        return studentId.equals(otherPredicate.studentId.toString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .toString();
    }
}
