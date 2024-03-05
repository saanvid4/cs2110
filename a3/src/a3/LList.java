package a3;

/**
 * An {@code LList<T>} is a mutable list of elements of type T.
 * Example: [1, 3, 2] is a list of Integers; [] is an empty list.
 */
public interface LList<T> extends Iterable<T> {

    /**
     * Returns: the number of list elements.
     */
    int size();

    /**
     * Returns: the first value in the list,
     * or null if the list is empty.
     */
    T head();

    /**
     * Returns: the last value in the list,
     * or null if the list is empty.
     */
    T tail();

    /**
     * Returns: the element at position k
     * Requires: {@code 0 <= k < size()}
     * <p>
     * Elements are indexed starting from position 0, so the
     * first element is returned if k=0.
     */
    T get(int k);

    /**
     * Effect: Insert v at the beginning of the list. This operation takes
     * constant time. E.g. if the list is [8, 7, 4], prepend(2) changes this
     * list to [2, 8, 7, 4].
     */
    void prepend(T v);

    /**
     * Effect: Removes x from the list. Returns true if x was successfully removed, otherwise
     * returns false. If there are more than one items in the list with a value x, the method will
     * remove the first instance of x from the list.
     * Example: If the list is [8, 7, 4], then remove(4) changes this list to [8, 7].
     */
    boolean remove(T x);

    /**
     * Effect: Add v to the end of this list. This operation takes constant time. E.g. if the list
     * is [8, 7, 4], append(2) changes this list to [8, 7, 4, 2].
     */
    void append(T v);

    /**
     * Effect: Insert value x into the list before the first occurrence of y.
     * Requires: y must be in the list.
     * Example: If the list is [3, 8, 2], then insertBefore(1, 8) results in the
     * the list changing to [3, 1, 8, 2].
     */
    void insertBefore(T x, T y);

    /**
     * Effect: Returns true if elem is in the list, and returns false if elem is not in the list.
     * Example: If the list is [3, 8, 2], then contains(8) returns true.
     */
    boolean contains(T elem);
}
