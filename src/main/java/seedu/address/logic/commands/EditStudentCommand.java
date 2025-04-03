package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_STUDENT_ID_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Address;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditStudentCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the student identified "
            + "by the student ID used in the displayed student list. \n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: Student ID "
            + "[" + PREFIX_STUDENT_NAME + "STUDENT_NAME] "
            + "[" + PREFIX_ID + "STUDENT_ID] "
            + "[" + PREFIX_PARENT_NAME + "PARENT_NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] \n"
            + "Example: " + COMMAND_WORD + " A01A "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";

    private final StudentId studentId;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param studentId of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(StudentId studentId, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(studentId);
        requireNonNull(editStudentDescriptor);

        this.studentId = studentId;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Student studentToEdit;
        try {
            studentToEdit = model.getStudentById(studentId);
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ID_NOT_FOUND, studentId));
        }

        Student editedstudent = createEditedstudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedstudent) && model.hasStudent(editedstudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedstudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedstudent)));
    }

    /**
     * Creates and returns a {@code student} with the details of {@code studentToEdit}
     * edited with {@code editstudentDescriptor}.
     */
    private static Student createEditedstudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedStudentName = editStudentDescriptor.getStudentName().orElse(studentToEdit.getStudentName());
        Name updatedParentName = editStudentDescriptor.getParentName().orElse(studentToEdit.getParentName());
        StudentId updatedSId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Attendance updatedAttendance = editStudentDescriptor.getAttendance().orElse(studentToEdit.getAttendance());

        return new Student(updatedStudentName, updatedSId, updatedParentName,
                updatedPhone, updatedEmail, updatedAddress, updatedAttendance);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        EditStudentCommand otherEditCommand = (EditStudentCommand) other;
        return studentId.equals(otherEditCommand.studentId)
                && editStudentDescriptor.equals(otherEditCommand.editStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .add("editStudentDescriptor", editStudentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name studentName;
        private Name parentName;
        private StudentId studentId;
        private Phone phone;
        private Email email;
        private Address address;
        private Attendance attendance;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentCommand.EditStudentDescriptor toCopy) {
            setStudentName(toCopy.studentName);
            setParentName(toCopy.parentName);
            setStudentId(toCopy.studentId);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setAttendance(toCopy.attendance);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(studentName, parentName, studentId, phone, email, address);
        }

        public void setStudentName(seedu.address.model.student.Name name) {
            this.studentName = name;
        }

        public void setParentName(seedu.address.model.student.Name name) {
            this.parentName = name;
        }


        public Optional<seedu.address.model.student.Name> getStudentName() {
            return Optional.ofNullable(studentName);
        }

        public Optional<Name> getParentName() {
            return Optional.ofNullable(parentName);
        }

        public void setStudentId(seedu.address.model.student.StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setPhone(seedu.address.model.student.Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(seedu.address.model.student.Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(seedu.address.model.student.Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAttendance(seedu.address.model.student.Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }



        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentCommand.EditStudentDescriptor)) {
                return false;
            }

            EditStudentCommand.EditStudentDescriptor otherEditstudentDescriptor =
                    (EditStudentCommand.EditStudentDescriptor) other;
            return Objects.equals(studentName, otherEditstudentDescriptor.studentName)
                    && Objects.equals(parentName, otherEditstudentDescriptor.parentName)
                    && Objects.equals(studentId, otherEditstudentDescriptor.studentId)
                    && Objects.equals(phone, otherEditstudentDescriptor.phone)
                    && Objects.equals(email, otherEditstudentDescriptor.email)
                    && Objects.equals(address, otherEditstudentDescriptor.address)
                    && Objects.equals(attendance, otherEditstudentDescriptor.attendance);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("studentName", studentName)
                    .add("parentName", parentName)
                    .add("studentId", studentId)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("attendance", attendance)
                    .toString();
        }
    }
}
