package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AttendanceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_noDate_doesNotThrowNullPointerException() {
        assertDoesNotThrow(() -> new Attendance(new HashSet<>()));
    }

    @Test
    public void getIsPresent_today_returnsTrue() {
        Attendance testAttendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.now())));
        assertTrue(testAttendance.getIsPresentToday());
    }

    @Test
    public void getIsPresent_minDate_returnsFalse() {
        Attendance testAttendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.MIN)));
        assertFalse(testAttendance.getIsPresentToday());
    }

    @Test
    public void getIsPresent_maxDate_returnsFalse() {
        Attendance testAttendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.MAX)));
        assertFalse(testAttendance.getIsPresentToday());
    }

    @Test
    public void getIsPresent_epochDate_returnsFalse() {
        Attendance testAttendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.EPOCH)));
        assertFalse(testAttendance.getIsPresentToday());
    }

    @Test
    public void getIsPresent_noDate_returnsFalse() {
        Attendance testAttendance = new Attendance(new HashSet<>());
        assertFalse(testAttendance.getIsPresentToday());
    }

    @Test
    public void setPresent_empty_returnsTrue() {
        Attendance testAttendance = new Attendance(new HashSet<>());
        testAttendance.setPresent();
        assertTrue(testAttendance.getIsPresentToday());
    }

    @Test
    public void setPresent_today_returnsTrue() {
        Attendance testAttendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.now())));
        testAttendance.setPresent();
        assertTrue(testAttendance.getIsPresentToday());
    }

    @Test
    public void setAbsent_empty_returnsFalse() {
        Attendance testAttendance = new Attendance(new HashSet<>());
        testAttendance.setAbsent();
        assertFalse(testAttendance.getIsPresentToday());
    }

    @Test
    public void setAbsent_today_returnsFalse() {
        Attendance testAttendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.now())));
        testAttendance.setAbsent();
        assertFalse(testAttendance.getIsPresentToday());
    }

    @Test
    public void getAttendance_empty_returnsEmptySet() {
        Set<LocalDate> test = new HashSet<>();
        Attendance testAttendance = new Attendance(test);
        assertTrue(testAttendance.getAttendance().equals(test));
    }

    @Test
    public void getAttendance_today_returnsEmptySet() {
        Set<LocalDate> test = new HashSet<>(Collections.singleton(LocalDate.now()));
        Attendance testAttendance = new Attendance(test);
        assertTrue(testAttendance.getAttendance().equals(test));
    }

    @Test
    public void equals() {
        Attendance attendance = new Attendance(new HashSet<>(Collections.singleton(LocalDate.now())));

        // same values -> returns true
        assertTrue(attendance.equals(new Attendance(new HashSet<>(Collections.singleton(LocalDate.now())))));

        // same object -> returns true
        assertTrue(attendance.equals(attendance));

        // null -> returns false
        assertFalse(attendance.equals(null));

        // different types -> returns false
        assertFalse(attendance.equals(5.0f));

        // different values -> returns false
        assertFalse(attendance.equals(new Attendance(new HashSet<>(Collections.singleton(LocalDate.EPOCH)))));
    }


}
