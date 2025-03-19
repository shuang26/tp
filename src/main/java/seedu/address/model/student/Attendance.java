package seedu.address.model.student;

/**
 * Represents a Person's attendance in the address book.
 */
public class Attendance {
    // initialize to false
    private boolean attendance;

    public Attendance(boolean attendance) {
        this.attendance = attendance;
    }

    public boolean isPresent() {
        return this.attendance;
    }

    public void setPresent() {
        this.attendance = true;
    }

    public void setAbsent() {
        this.attendance = false;
    }

    public boolean getAttendance() {
        return this.attendance;
    }
}
