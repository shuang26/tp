package seedu.address.model.student;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s student ID matches the provided student ID (no case sensitivity).
 */
public class StudentIdEqualsPredicate implements Predicate<Student> {
    private final StudentId studentId;

    public StudentIdEqualsPredicate(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean test(Student student) {
        return student.getStudentId().toString().equalsIgnoreCase(studentId.toString());
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
        return studentId.toString().equals(otherPredicate.studentId.toString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .toString();
    }
}
