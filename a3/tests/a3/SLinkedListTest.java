package a3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SLinkedListTest {

    @Test
    void emptyList() {
        LList<Integer> lst0 = new SLinkedList<>();
        assertEquals(lst0.size(), 0);
    }

    @Test
    void append12() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
    }

    @Test
    void append13() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
        l1.append(21);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 21);
    }

    @Test
    void append14() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
        l1.append(21);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 21);
        l1.append(36);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 36);
    }

    @Test
    void append15() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
        l1.append(21);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 21);
        l1.append(14);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 14);
        l1.append(55);
        assertEquals(l1.size(), 4);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 55);
        assertEquals(l1.get(2), 14);
    }

    @Test
    void prepend12() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.prepend(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
        l1.prepend(21);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 21);
        assertEquals(l1.tail(), 42);
    }

    @Test
    void prepend11() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.prepend(42);
        l1.prepend(21);
        l1.prepend(10);
        l1.prepend(33);
        assertEquals(l1.size(), 4);
        assertEquals(l1.head(), 33);
        assertEquals(l1.tail(), 42);
    }

    @Test
    void prepend13() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.prepend(26);
        l1.prepend(11);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 11);
        assertEquals(l1.tail(), 26);
    }

    @Test
    void prepend14() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.prepend(42);
        l1.prepend(99);
        l1.prepend(99);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 99);
        assertEquals(l1.tail(), 42);
        assertEquals(l1.get(1), 99);
    }

    @Test
    void test_get() {
        LList<Integer> lst = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            lst.append(i);
        }
        assertEquals(lst.size(), 5);
        for (int i = 0; i < 5; i++) {
            assertEquals(i, lst.get(i));
            lst.append(i);
        }
    }

    @Test
    void get1() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        l1.append(31);
        l1.append(18);
        l1.append(55);
        l1.append(2);
        assertEquals(l1.get(0), 42);
        assertEquals(l1.get(l1.size() - 1), 2);
    }

    @Test
    void get2() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.prepend(42);
        l1.append(31);
        l1.prepend(18);
        l1.append(55);
        l1.prepend(2);
        assertEquals(l1.get(0), 2);
        assertEquals(l1.tail(), 55);
    }

    @Test
    void get3() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        l1.insertBefore(31, 42);
        l1.append(18);
        l1.insertBefore(55, 18);
        l1.append(2);
        assertEquals(l1.get(1), 42);
        assertEquals(l1.tail(), 2);
    }

    @Test
    void toString1() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        assertEquals("[]", l1.toString());
        assertEquals(0,l1.size());
        l1.append(42);
        l1.append(13);
        l1.append(54);
        l1.append(34);
        l1.append(28);
        assertEquals(5,l1.size());


        //System.out.println(l1.toString());
    }

    @Test
    void insertBefore1() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        l1.append(31);
        l1.append(18);
        l1.insertBefore(66, 31);
        assertEquals(l1.size(), 4);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 18);
        assertEquals(l1.get(1), 66);
        System.out.println(l1.toString());
    }

    @Test
    void insertBefore2() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        l1.append(31);
        l1.append(18);
        l1.insertBefore(66, 18);
        System.out.println(l1.toString());
        assertEquals(l1.size(), 4);
        assertEquals(l1.get(2), 66);
    }

    @Test
    void insertBefore3() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(10);
        l1.append(31);
        l1.append(18);
        l1.insertBefore(10, 31);
        System.out.println(l1.toString());
        assertEquals(l1.size(), 4);
        assertEquals(l1.get(0), 10);
    }

    @Test
    void contains() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(42);
        l1.append(31);
        l1.append(18);
        assertEquals(l1.contains(55), false);
        assertEquals(l1.contains(31), true);
        assertEquals(l1.contains(55), false);
        assertEquals(l1.contains(null), false);
    }

    @Test
    void remove1() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(42);
        l1.append(31);
        l1.append(55);
        assertEquals(l1.remove(42), true);
        assertEquals(l1.size(), 2);
    }

    @Test
    void remove2() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(42);
        l1.append(31);
        l1.append(55);
        assertEquals(l1.remove(64), false);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 55);
    }

    @Test
    void remove3() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(22);
        assertEquals(l1.remove(22), true);
        assertEquals(l1.size(), 0);
    }


}
