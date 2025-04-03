package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Student's attendance in the address book.
 */
public class Attendance {
    private boolean isPresentToday;

    private final Set<LocalDate> attendance = new HashSet<>();

    /**
     * Initialises attendance.
     * @param attendance Set of dates where student was present.
     */
    public Attendance(Set<LocalDate> attendance) {
        requireNonNull(attendance);
        this.attendance.addAll(attendance);
        this.isPresentToday = this.attendance.contains(LocalDate.now());
    }

    /**
     * Returns whether student is present today.
     * @return True if present today, False otherwise.
     */
    public boolean getIsPresentToday() {
        return this.isPresentToday;
    }

    /**
     * Sets student to present today.
     */
    public void setPresent() {
        this.attendance.add(LocalDate.now());
        this.isPresentToday = true;
    }

    /**
     * Sets student to absent today.
     */
    public void setAbsent() {
        this.attendance.remove(LocalDate.now());
        this.isPresentToday = false;
    }

    /**
     * Gets set of dates where student was present.
     * @return Set of dates
     */
    public Set<LocalDate> getAttendance() {
        return this.attendance;
    }

    @Override
    public String toString() {
        return attendance.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherEmail = (Attendance) other;
        return attendance.equals(otherEmail.attendance);
    }

    @Override
    public int hashCode() {
        return attendance.hashCode();
    }
}
