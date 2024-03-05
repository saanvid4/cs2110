package a2;

/** Test harness for Assignment 2
 */
public class A2Test {
    public static void main(String[] args) {
        testEmptyList();
        testAppend();
        testRemove();
        // TODO Add more tests to thoroughly test StudentList
        //
        // The methods provided do not necessarily test everything in each
        // case.  You will need to add more to the existing testing procedures
        // as well as add new testing procedures.  You can also add tests to
        // test the Course and Student classes.
        // 
        // Try to keep tests small and test features as independently as
        // possible.
    }

    public static void testEmptyList() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 0");
        System.out.println("list.contains(s) = " + list.contains(s));
        System.out.println("expected = false");
    }

    public static void testAppend() {
        StudentList list = new StudentList();
        Student s1 = new Student("Bill", "n", 4);
        Student s2 = new Student("Bill", "y", 4);
        Student s3 = new Student("Bill", "i", 4);
        Student s4 = new Student("Bill", "o", 4);
        Student s5 = new Student("Bill", "p", 4);
        list.append(s1);
        list.append(s2);
        list.append(s3);
        list.append(s4);
        list.append(s5);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 5");
    }

    public static void testRemove() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "n", 4);
        list.append(s);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 1");
        list.remove(s);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 0");
    }
}