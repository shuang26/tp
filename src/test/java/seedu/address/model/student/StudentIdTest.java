package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId));
    }

    @Test
    public void isValidId() {
        // null student ID
        assertThrows(NullPointerException.class, () -> StudentId.isValidId(null));

        // blank Student ID
        assertFalse(StudentId.isValidId("")); // empty string
        assertFalse(StudentId.isValidId(" ")); // spaces only

        // incomplete Student ID
        assertFalse(StudentId.isValidId("A9P")); // less than 2 numbers
        assertFalse(StudentId.isValidId("09P")); // missing A
        assertFalse(StudentId.isValidId("A23")); // missing alphabet
        assertFalse(StudentId.isValidId("91")); // no alphabet
        assertFalse(StudentId.isValidId("AP")); // no number

        // other invalid Student ID
        assertFalse(StudentId.isValidId("A089L")); // more than 2 numbers
        assertFalse(StudentId.isValidId("M23N")); // not beginning with A
        assertFalse(StudentId.isValidId("A2 3M")); // spaces within ID

        // valid Student ID
        assertTrue(StudentId.isValidId("A69W"));
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("A27S");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("A27S")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("A00L")));
    }
}
