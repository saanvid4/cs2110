package a3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of {@code LList<T>} as a singly linked list.
 */
public class SLinkedList<T> implements LList<T> {

    /**
     * Number of values in the linked list.
     */
    private int size;
    /**
     * First and last nodes of the linked list (null if size is 0)
     */
    private Node<T> head, tail;

    /**
     * Creates: an empty linked list.
     */
    public SLinkedList() {
        size = 0;
        head = tail = null;
        assert classInv();
    }

    /**
     * size of list should be > 0 if size is = to 0, head and tail should both = null tail should be
     * last element and anything after tail should be null if size is 1, head and tail should be
     * equal and point to the same element if size is not 0 or 1, head and tail should point to same
     * element
     *
     * @return
     */

    boolean classInv() {
        assert size >= 0;
        if (size == 0) {
            return (head == null && tail == null);
        }
        assert (tail.next() == null)[]
        if (size == 1) {
            return (head.equals(tail));
        } else {
            return (!head.equals(tail));
        }
        // TODO
    }

    public int size() {
        assert classInv();
        return size;
    }

    public T head() {
        assert classInv();
        if (head == null) {
            return null;
        }
        return head.data();
    }

    public T tail() {
        assert classInv();
        if (tail == null) {
            return null;
        }
        return tail.data();
    }


    public void prepend(T v) {
        assert classInv();
        Node<T> n = new Node<>(v, head);
        head = n;
        if (tail == null) {
            tail = head;
        }
        size++;
        assert classInv();
    }


    /**
     * Return a representation of this list: its values, with "[" at the beginning, "]" at the end,
     * and adjacent ones separated by ", " . Takes time proportional to the length of this list.
     * E.g. for the list containing 4 7 8 in that order, the result it "[4, 7, 8]". E.g. for the
     * list containing two empty strings, the result is "[, ]"
     */
    @Override
    public String toString() {
        // Do not modify the following 2 lines or the return statement
        assert classInv();
        StringBuilder res = new StringBuilder("[");
        Node<T> n = head;
        // TODO 1
        if (n != null) {
            res.append(n.data());
            for (int i = 1; i < size; i++) {
                n = n.next();
                res.append(", " + n.data());
            }
        }
        return res + "]";
    }

    public void append(T v) {
        // TODO 2
        assert classInv();
        Node<T> n = new Node<T>(v, null);
        if (tail != null) {
            tail.setNext(n);
            tail = n;
        } else {
            head = n;
            tail = n;
        }
        size++;
        assert classInv();
    }

    public void insertBefore(T x, T y) {
        // TODO 3
        // since there is a precondition that y is in the list, we don't have to handle the case of the empty list
        assert classInv();
        Node<T> n = head;
        if (head.data()==y) {
            prepend(x);
        } else {
            while (!n.next().data().equals(y)) {
                n = n.next();
            }
            Node<T> n1 = new Node<T>(x, n.next());
            n.setNext(n1);
            size++;
            }


            assert classInv();
        }


    public T get(int k) {
        // TODO 4
        assert classInv();
        Node<T> n = head;
        for (int i = 0; i < k; i++) {
            n = n.next();
        }
        return n.data();
    }

    public boolean contains(T value) {
        // TODO 5
        assert classInv();
        Node<T> n = head;
        while (n != null) {
            if (n.data().equals(value)) {
                return true;
            } else {
                n = n.next();
            }
        }
        return false;
    }

    public boolean remove(T x) {
        assert classInv();
        Node<T> n = head;
        if (size == 0) {
            return false;
        }

        if (head.data().equals(x)) {
            head = head.next();
            if (size == 1) {
                tail = null;
            }
            size--;
            return true;
        }
        while (n.next() != null) {
            if (n.next().data().equals(x)) {

                n.setNext(n.next().next());
                if(n.next()==null){
                    tail=n;
                }
                size--;
                return true;
            }
            n = n.next();
        }
        return false;
        // TODO 6
    }

    /**
     * Iterator support. This method makes it possible to write a for-loop over a list, e.g.:
     * <pre>
     * {@code LList<String> lst = ... ;}
     * {@code for (String s : lst) {}
     *   ... use s here ...
     * }
     * }
     */
    @Override
    public Iterator<T> iterator() {
        assert classInv();
        return new Iterator<T>() {
            private Node<T> current = head;

            public T next() throws NoSuchElementException {
                if (current != null) {
                    T result = current.data();
                    current = current.next();
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolean hasNext() {
                return (current != null);
            }
        };
    }
}
