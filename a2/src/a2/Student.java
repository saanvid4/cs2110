package a2;

public class Student {

    private String firstName;
    private String lastName;
    private int year;
    private Course course;

    // TODO 1 Add instance variables and write the class invariant
    // Declare the following fields. These fields will hold information describing each Student.
    // Make the fields private and include comments describing each of them in the form of a class invariant
    // * firstName. First name of this Student. Must be a non-empty String.
    // * lastName. Last name of this Student. Must be a non-empty String.
    // * year. The year this Student is in school. E.g. 1 if Freshman, 2 if Sophomore, etc. Must be > 0
    // * course. The Course this Student is enrolled in. This Student may be enrolled in at most 1 course.
    //  null if this Student is not enrolled in any course.

    /** Constructor: Create a new Student with first name f, last name l, and year y.
     * This student is not enrolled in any Courses.
     * Requires: f and l have at least one character and y > 0. */

    /**
     * class invariant: private String firstName is the student's first name; length must be greater
     * than 0 private String lastName is the student's last name; length must be greater than 0
     * private int year is the student's grade; must be greater than 0 and less than or equal to 4
     * private Course course is the course the student is enrolled in;
     */
    public boolean classInv() {
        return (firstName.length() > 0 && lastName.length() > 0 && year <= 4 && year > 0);
    }

    public Student(String f, String l, int y) {
        firstName = f;
        lastName = l;
        year = y;
        course = null;
        assert classInv();
        //TODO 2
    }


    /**
     * The first name of this Student.
     */
    public String firstName() {
        assert classInv();
        // TODO 3
        return firstName;
    }

    /**
     * The last name of this Student.
     */
    public String lastName() {
        assert classInv();
        // TODO 4
        return lastName;
    }

    /**
     * The first and last name of this Student in the format "First Last".
     */
    public String fullName() {
        assert classInv();
        // TODO 5
        return firstName + " " + lastName;
    }

    /**
     * The year in school this Student is in.
     */
    public int year() {
        assert classInv();
        // TODO 6
        return year;
    }

    /**
     * The course this student is enrolled in.
     */
    public Course course() {
        assert classInv();
        // TODO 7
        return course;
    }

    /**
     * Enroll this Student in Course c. Requires: The student is not enrolled in a course already.
     */
    public void joinCourse(Course c) {
        assert classInv();
        if (course == null) {
            course = c;
        }
        assert classInv();
        // TODO 8
    }

    /**
     * Drop this Student from their Course. Requires: This student is enrolled in a course already.
     */
    public void leaveCourse() {
        assert classInv();
        if (course != null) {
            course = null;
        }
        assert classInv();
        // TODO 9
    }

    /**
     * Return the full name of this Student
     */
    public String toString() {
        assert classInv();
        return fullName();
    }
}