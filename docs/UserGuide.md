---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

## CareBook User Guide

Welcome to CareBook! **Your classroom, organized.**

Designed with daycare teachers in mind, **CareBook** simplifies **daily roster management and parent communication** so you can focus on what matters the most - caring for children.

With CareBook, you can:
- Instantly access **students’ and parents’ contacts**
- Streamline **daily attendance tracking** with simple commands
- Easily maintain **student records** for smooth communication

This user guide will walk you through:
- Setting up your CareBook application
- Navigating the Graphical User Interface (GUI) and Command Line Interface(CLI)
- Learning essential commands for managing contacts and attendance

By the end of this guide, you’ll be a **CareBook pro**, managing your daycare with ease and precision.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about daycare teachers:**<br>

* We assume that the daycare teachers managing daily rosters of 30 to 40 children

</div>

***
## Table of Contents
- Quick start
    - [Installing CareBook](#installation)
    - [Introducing GUI](#introducing-gui)
- Features
    - [Adding a student:](#adding-a-student) `add`
    - [Listing all students:](#listing-all-students) `list`
    - [Editing a student:](#editing-a-student) `edit`
    - [Deleting a student:](#deleting-a-student) `delete`
    - [Exiting the program:](#exiting-the-program) `exit`
    - [Finding a student’s contact:](#finding-a-student-s-contact)`find`
    - [Viewing help:](#viewing-help) `help`
    - [Marking a student’s attendance:](#marking-a-student-s-attendance) `mark`
    - [Unmarking a student’s attendance:](#unmarking-a-student-s-attendance) `unmark`
    - [Marking all students’ attendance:](#marking-all-students-attendance) `markall`
    - [Unmarking all students’ attendance:](#unmarking-all-students-attendance) `unmarkall`
    - [Exporting attendance summary:](#exporting-attendance-summary) `export`
- [FAQ](#faq)
- [Command Summary](#command-summary)
***

## Quick Start

#### Installation
1. Ensure you have Java `17` or above installed in your computer.
   <br>**Mac Users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html)
   <panel header="How to check your java version">
   <markdown>
   In your command terminal, type `java -version`
   </markdown>
   </panel>

2. Download `.jar` file with the latest release [here](https://github.com/AY2425S2-CS2103T-T11-2/tp/releases/tag/v1.3).

3. Move the `.jar` file to the folder you want to use as the home folder for your CareBook

4. Open a command terminal, `cd` into the folder you placed your .jar file and type `java -jar carebook.jar` and press enter to run CareBook application.
   <panel header="Changing directory">
   <markdown>
   If your folder is under `/home/user/CareBook`, simply type `cd /home/user/CareBook` and hit enter
   </markdown>
   </panel>

#### Introducing GUI
After successfully running CareBook application, a GUI similar to below should be displayed.

<img src="images/Ui.png" width="600">

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5;">
  💡 <strong>Tip:</strong> CareBook provides sample data in the first run.
</div>

***

## Features

#### Adding a student
Adds a student to CareBook.

**Command format:** `add <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>`

**Additional Information:**
* Parameters can be added in _any order_, but all are compulsory.
* No restrictions to StudentName, ParentName and Address.
* Student ID should be **4 characters** beginning with A followed by 2 digits and ending with an alphabet.
* Phone number should be from 80000000 to 99999999.
* Email address should have an @ domain.

**Examples:**
* `add sn/Tom Hank id/A10A pn/Thomas Hank p/98122012 e/thomashank@hotmail.com a/Bukit View 9`
* `add sn/Jane Cena id/A99Z pn/John Cena p/91029322 e/janeCena@hotmail.com a/Canberra Drive 9`

<p>&nbsp;</p>

#### Listing all students
Lists all students in CareBook.

**Command format:** `list`

<p>&nbsp;</p>

#### Editing a student
Edits an existing student in CareBook.

**Command format:** `edit <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>`

**Additional Information:**
* No restrictions to StudentName, ParentName and Address.
* Student ID should be **4 characters** beginning with A followed by 2 digits and ending with an alphabet.
* Edited Student ID should be a new Student ID that has not been assigned to any students.
* Phone number should be from 80000000 to 99999999.
* Email address should have an @ domain.

**Examples:**
* `edit sn/Tom Hank id/A10A pn/Thomas Hank p/98122012 e/thomashank@hotmail.com a/Bukit View 9`
* `edit sn/Jane Cena id/A99Z pn/John Cena p/91029322 e/johnnyCena@gmail.com a/Canberra Drive 9`

<p>&nbsp;</p>

#### Deleting a student
Deletes a student from CareBook.

**Command format:** `delete <StudentId>`

**Additional Information:**
* Student ID should be one that already exists in CareBook.

**Examples:**
* `delete A10A`
* `delete A09B`

<p>&nbsp;</p>

#### Exiting the program
Exits the program.

**Command format:** `exit`

<p>&nbsp;</p>

#### Finding a student’s contact
Finds a specific student with matching Student ID.

**Command format:** `find <StudentId>`

**Additional Information:**
* Student ID is case-sensitive (e.g. “a01a” will not match “A01A”).
* Student ID must be one that already exists in CareBook.

**Examples:**
* `find A03A`
* `find A99Z`

<p>&nbsp;</p>

#### Viewing help
Opens a help window that redirects users to CareBook user guide website.

**Command format:** `help`

<p>&nbsp;</p>

#### Marking a student’s attendance
Marks a student in CareBook as present.

**Command format:** `mark <StudentId>`

**Additional Information:**
* Student ID is case-sensitive (e.g. “a01a” will not match “A01A”).
* Student ID must be one that already exists in CareBook.

**Examples:**
* `mark A10A`
* `mark A99Z`

<p>&nbsp;</p>

#### Marking all students’ attendance
Marks all students in CareBook as present.

**Command format:** `markall`

**Additional Information:**
* The command only works if there is at least one recorded student in CareBook.

<p>&nbsp;</p>

#### Unmarking a student’s attendance
Marks a student in CareBook as absent.

**Command format:** `unmark <StudentId>`

**Additional Information:**
* Student ID is case-sensitive (e.g. “a01a” will not match “A01A”).
* Student ID must be one that already exists in CareBook.

**Examples:**
* `unmark A10A`
* `unmark A99Z`

<p>&nbsp;</p>

#### Unmarking all students’ attendance
Marks all students in CareBook as absent.

**Command format:** `unmarkall`

**Additional Information:**
* The command only works if there is at least one recorded student in CareBook.

<p>&nbsp;</p>

#### Exporting attendance summary
Exports student ID, student name, parent name, parent’s email, parent number and attendance history of students recorded 
in CareBook. Attendance summary is exported to the current directory where CareBook application is stored in.

**Command format:** `export <fileName>`

**Additional Information:**

* File name should only consist of alphanumeric characters and underscores (_). Special characters including 
but not limited to * . / ‘ are invalid.

**Examples**
* `export attendance_sheet`
* `export student_records`

<p>&nbsp;</p>

***

## FAQ

**1. Do I need an internet connection to use CareBook?**   
No, CareBook works **fully offline**. All data is stored locally on your device.

**2. Is my data automatically saved?**   
Yes, CareBook **automatically saves** all changes after each command. No need to manually save!

***

## Command Summary
| Action        | Command Format/Examples                                                                                                                                                                |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**       | `add <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>`<br/> e.g. add sn/Tom Hanks id/A10A pn/Henry Hanks p/99019232 e/henry@hotmail.com a/Bukit View 9  |
| **Clear**     | `clear`                                                                                                                                                                                |
| **Delete**    | `delete <StudentId>` <br/> e.g. delete A10A                                                                                                                                            |
| **Edit**      | `edit <studentId> <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>` <br/> e.g. edit A10A p/91092222 e/henryhanks@hotmail.com |
| **Exit**      | `exit`                                                                                                                                                                                 |
| **Export** | `export <fileName>`<br/> e.g. export attendance_sheet                                                                                                                                  |
| **Find**      | `find <StudentId>` <br/> e.g. find A10A                                                                                                                                                |
| **Help**      | `help`                                                                                                                                                                                 |
| **List**      | `list`                                                                                                                                                                                 |
| **Mark**      | `mark <StudentId>` <br/> e.g. mark A10A                                                                                                                                                |
| **Markall**   | `markall`                                                                                                                                                                              |
| **Unmark**    | `unmark <StudentId>` <br/> e.g. mark A10A                                                                                                                                              |
| **Unmarkall** | `unmarkall`                                                                                                                                                                            |
