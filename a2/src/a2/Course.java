package a2;

public class Course {

    /**
     * List of all students enrolled in this Course.
     */
    private StudentList students;
    /**
     * The hour at which lecture for this Course is held (in 24-hour time). 0 <= hour <= 23
     */
    private int hour;
    /**
     * The minutes at which lecture for this Course is held. 0 <= min <= 59 The lectures for this
     * course are at hour:min
     */
    private int min;
    /**
     * The location of lectures at this course (e.g. Statler Hall Room 185) Must be non-empty
     */
    private String location;
    /**
     * The last name of the professor of this course (e.g. Myers, Muhlberger, Gries) Must be
     * non-empty
     */
    private String prof;
    /**
     * The name of this course (e.g. Object-Oriented Programming and Data Structures) Must be
     * non-empty
     */
    private String name;

    /**
     * class invariant: private int hour is the hour at which this Course is held; must be greater
     * than or equal to 0 and less than or equal to 23 private int min is the minutes at which this
     * Course is held; must be greater than or equal to 0 and less than or equal to 59 private
     * String location is the location of this course; length must be greater than 0; private String
     * prof is the last name of the professor of this course; length must be greater than 0; private
     * String name is the name of this course; length must be greater than 0;
     */
    public boolean classInv() {
        return ((hour <= 23) && (hour >= 0) && (min >= 0) && (min <= 59) && (location.length() > 0)
                && (prof.length() > 0) && (name.length() > 0));
    }

    /**
     * Constructor: Create new Course with name n, professor last name prof, location loc,<br> and
     * lectures are held at time hour:min. The course has no students. Precondition: n, prof, and
     * loc have at least one character in them, 0 <= hr <= 23, 0 <= min <= 59.
     */
    public Course(int hr, int min, String loc, String prof, String n) {
        name = n;
        this.prof = prof;
        location = loc;
        this.min = min;
        hour = hr;
        // TODO 18
        // Note that an empty StudentList is not the same as null
        students = new StudentList();
        assert classInv();
    }

    /**
     * Return the name of this course.
     */
    public String getName() {
        assert classInv();
        // TODO 19
        return name;
    }

    /**
     * Return the time at which lectures are held for this course in the format hour:min AM/PM using
     * 12-hour time. For example, "11:15 AM", "1:35 PM". Add leading zeros to the minutes if
     * necessary.
     */
    public String getLectureTime() {
        String time = null;
        String zero = "";
        int hour12 = hour;
        assert classInv();

        if (min < 10) {
            zero = "0";
        }

        if (hour == 0) {
            hour12 = 12;
            return hour12 + ":" + zero + min + " " + "AM";
        } else if (hour < 12) {
            time = "AM";
            return hour12 + ":" + zero + min + " " + time;
        } else if (hour == 12) {
            time = "PM";
            return hour12 + ":" + zero + min + " " + time;
        } else {
            time = "PM";
            hour12 = hour - 12;
            return hour12 + ":" + zero + min + " " + time;
        }
        // TODO 20
    }

    /**
     * Return the location of lectures in this course.
     */
    public String getLocation() {
        assert classInv();
        // TODO 21
        return location;
    }

    /**
     * Return the name of the professor in the format "Professor LastName"
     */
    public String getProfessor() {
        assert classInv();
        // TODO 22
        return "Professor " + prof;
    }

    /**
     * Return the String representation of the list of students enrolled in this course
     */
    public String getStudents() {
        assert classInv();
        //TODO 23
        return "" + students;
    }

    /**
     * Enroll a new student s to this course. If Student s is already enrolled in a course, they
     * cannot enroll in this course. Return true if the student was successfully enrolled in the
     * course.
     */
    public boolean enrollStudent(Student s) {
        assert classInv();
        if (s.course() == null) {
            s.joinCourse(this);
            students.append(s);
            return true;
        }
        return false;
        // TODO 24
        // Remember that the class invariant of all classes must be kept true.
        // In other words, make sure that every field is correctly modified based on its
        // Javadoc comments.
    }

    /**
     * Drop Student s from this course. If Student s is not enrolled in this course, they cannot be
     * dropped from this course. Return true if the student was successfully dropped from the
     * course.
     */
    public boolean dropStudent(Student s) {
        assert classInv();
        if (students.contains(s)) {
            s.leaveCourse();
            students.remove(s);
            return true;
        }
        // TODO 25
        // Remember that the class invariant of all classes must be kept true.
        // In other words, make sure that every field is correctly modified based on its
        // Javadoc comments.
        return false;
    }

    /**
     * Print the Course information in tabular format
     */
    public void print() {
        assert classInv();
        System.out.printf("%-40s%-12s%-20s%-40s",
                name, getLectureTime(), prof, location);
    }
}