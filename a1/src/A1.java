/** Please provide the following information
 * Name(Ss): Saanvi Dhaniyala
 * NetID(s): sd722
 * Tell us what you thought about the assignment: I thought this assignment was pretty easy and good review of Java syntax and basic concepts
 */

/** The goal of this assignment is to familiarize yourself with Java and start
 * to establish good programming practices.
 */

/** Class A1 defines a set of methods to implement. Each method has a comment
 * at the top. These are method specifications (specs) Method specs allow
 * anyone who is reading the program to immediately understand what the method
 * is doing.
 *
 * Each function body has in it a return statement. Without it, the function
 * won't compile. Replace the return statement with code you write to implement
 * the spec.
 */
import static java.lang.Math.pow;

public class A1 {

    /** Replace the "-1" with the amount of time you spent on A1 in hours.
     *  Example: for 1 hour and 30 min, write that timeSpent = 1.5
     *  Example: for 1 hour, write that timeSpent = 1 or timeSpent = 1.0
     */
    public static double timeSpent = -1;

    /** Return the product of the values: 7, 11, and 13. */
    public static int product() {
        
        return 7*11*13;
    }

    /** In the following order: double x, add 4, divide it by 2, and then
     *  subtract the original x value from the result. */
    public static int theAnsweris2(int x){
        return (((x*2)+4)/2)-x;
    }

    /** Given some temperature x of water in degrees Celsius,that the
     * melting point of water is 0ºC , and that the boiling point is 100ºC,
     * determine if the water is liquid. Note: in our program water is not
     * a liquid at 0ºC or 100ºC.
     */
    public static boolean isLiquid(int temperature){
        if ((temperature > 0) && (temperature < 100)) {
            return true;
        }
        else {
            return false;
        }
    }

    /** If x is not a three-digit number, return -1;
     * Otherwise return the product of x and the values: 7, 11, and 13.
     */
    public static int magicTrick(int x) {
        if ((x/100 < 1) && (x/100 > 9))
            return -1;
        else
            return x*7*11*13;
    }

    /** Given some value x, return 42 if x is equal to 42. If not, return -1.
     */
    public static int theAnsweris42Conditional(int x){
        if (x==42)
            return 42;
        else
            return -1;
    }   

    /** Given two triangle legs a and b of a right triangle, return
     *  the length of the hypotenuse.
     *  Requires: a and b must be positive values.
     */
    public static double hypotenuse(double a, double b) {
        return pow(((a*a)+(b*b)),0.5);
    }

    /** Given three triangle side lengths a, b, and c, determine if such a
     *  triangle can exist.
     */
    public static boolean isTriangle(double a, double b, double c) {
        if ((a+b<c) || (b+c<a) || (c+a<b))
            return false;
        else 
            return true;
    }

    /** Given four operations: ADD, SUBTRACT, MULTIPLY, and DIVIDE and two
     *  inputs x and y, return the result of the operation between x and y.
     *  Requires:
     *      - op is one of "ADD", "SUBTRACT", "MULTIPLY", or "DIVIDE".
     *      - if opp = "DIVIDE", y is not 0.
     */
    public static int calculate(String opp, int x, int y) {
        switch (opp) {
            case "ADD":
                return(x+y);
            case "SUBTRACT":
                return(x-y);
            case "MULTIPLY":
                return(x*y);
            case "DIVIDE":
                return(x/y);
        }
        return -1;
    }

    /** Return the sum of the values in n to m inclusive.
     *  Requires: n <= m
     */
    public static int rangeSum(int n, int m) {
        int sum = 0;
        for(int i=n; i <= m; i++) {
            sum += i;
        }
        return sum;
    }

    /** Return the sum of the odd values in n to m inclusive.
     *  Requires: n <= m
     */
    public static int rangeSumOdd(int n, int m) {
        int sum = 0;
        for(int i=n; i <= m; i++) {
            if (i%2==1) 
                sum += i;
        }
        return sum;
    }

    /** Return whether str is a palindrome.
     */
    public static boolean isPalindrome(String str) {
        int length = str.length();
        for(int i=0; i < length/2+1; i++) {
            if(!str.substring(i,i+1).equals(str.substring(length-i-1,length-i))) {
                return false;
            }
        }
        return true;
    }

    /** Return a string that contains the same characters as str, but with each
     *  vowel duplicated.
     */
    public static String duplicateVowels(String str) {
        int length = str.length();
        String newstr = "";
        char c;
        for (int i=0; i < length; i++) {
            c = str.charAt(i);
            newstr+=c+"";
            if((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u'))
                newstr+=c+"";
        }
        return newstr;
    }
}
