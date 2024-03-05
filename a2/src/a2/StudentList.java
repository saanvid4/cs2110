package a2;
/**
 * Please provide the following information
 *  Name: Saanvi Dhaniyala
 *  NetID: sd722
 *  Testing Partner Name: Riley Coogan
 *  Testing Partner NetID: rmc329
 *  Tell us what you thought about the assignment: I thought the assignment was a good way to learn class invariants. It was a little long and took me a lot longer than assignment 1.
 */

/** A mutable list of {@code Student} objects.
 */
public class StudentList {

    private Student[] student_list;
    private int size;
    private int capacity;
    // Implementation: the StudentList is implemented
    // as a resizable array data structure. It should contain
    // an array that has a large enough capacity to hold all the elements,
    // plus possibly extra elements. It should be able to hold
    // a large number of students but not use up a large amount
    // of memory if it holds a small number of students.

    // TODO 10: Add instance variables for the StudentList and write a class invariant
    // You may not use any classes from the java.util package.

    /** How long you spent on this assignment */
    public static double timeSpent = 5.0;

    /** class invariant: private int capacity is the number of elements the list can hold. Must be greater than 0.
     * */
    public boolean classInv() {
        return (capacity > 0);
    }

    /** Constructor: A new empty {@code StudentList}. */
    public StudentList() {
        capacity = 5;
        student_list = new Student[capacity];
        // TODO 11
        // The capacity of the StudentList is not the same as the size.
        // The capacity is the length of the backing array.
        // We suggest starting with a capacity of 5.
        // If the backing array runs out of space, double the size of the backing array
        // and copy all elements to the new backing array
    }

    /** The number of elements in this StudentList. */
    public int size() {
        assert classInv();
        // TODO 12
        // Note: Do not confuse size and capacity.
        return size;
    }

    /** The element in the list at position i. Positions start from 0.
     * Requires: 0 <= i < size of StudentList
     */
    public Student get(int i) {
        assert classInv();
        // TODO 13
        return student_list[i];
    }

    /** Effect: Add Student s to the end of the list. */
    public void append(Student s) {
        assert classInv();
        assert s.classInv();
        if (size >= capacity) {
            student_list = doublearray();

        }

        student_list[size] = s;
        size++;
        assert classInv();
    }
        // TODO 14
        // Make sure that BEFORE adding a Student to the array, you
        // ensure that the capacity of the array is enough to add a
        // Student to it.
        // Note: Make sure you are keeping the class invariant for ALL classes true.



    /** Returns: whether this list contains Student s. */
    public boolean contains(Student s) {
        assert classInv();
        assert s.classInv();
        for (int i = 0; i < size; i++) {
            if (student_list[i].equals(s))
                return true;
        }
        // TODO 15
        return false;
    }

    /** Effect: If Student s is in this StudentList, remove Student s from the array and return true.
     * Otherwise return false. Elements other than s remain in the same relative order.
     */
    public boolean remove(Student s) {
        assert classInv();
        assert s.classInv();
        if(contains(s)) {
            for (int i=0; i < size; i++) {
                if (student_list[i].equals(s)) {
                    student_list[i] = null;
                    for (int j = i; j < size; j++) {
                        student_list[j] = student_list[j + 1];
                    }
                }
            }
        size--;
        return true;
        }
        return false;

        // TODO 16
        // Note: Make sure you are keeping the class invariant for ALL classes true.
    }
    /**
     * Effect: creates a new list with a length that is double the size of the current list and transfers all the elements of the current list to the new list.
     * @return: a list of students with a capacity of size*2
     * */
    public Student[] doublearray() {
        assert classInv();
        capacity = size*2;
        Student[] newstudent_list = new Student[capacity];
        for (int i = 0; i < size; i++) {
            newstudent_list[i] = student_list[i];
        }
        return newstudent_list;
    }

    // TODO 17 you may want to write some private helper methods

    /** The String representation of this StudentList */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
