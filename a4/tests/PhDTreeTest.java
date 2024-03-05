package a4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Amy Huang, Maya Leong
 */
public class PhDTreeTest {

    private static final Professor prof1 = new Professor("Amy", "Huang");
    private static final Professor prof2 = new Professor("Maya", "Leong");
    private static final Professor prof3 = new Professor("Matthew", "Hui");
    private static final Professor prof4 = new Professor("Arianna", "Curillo");
    private static final Professor prof5 = new Professor("Michelle", "Gao");
    private static final Professor prof6 = new Professor("Isa", "Siu");

    private static final Professor prof7 = new Professor("Anjali", "Kesari");
    private static final Professor prof8 = new Professor("Saanvi", "Dhaniyala");

    private static final Professor prof9 = new Professor("Riley", "Coogan");


    private static PhDTree tree1() {
        return new PhDTree(prof1, 2023);
    }

    private static PhDTree tree2() {
        return new PhDTree(prof4, 2019);
    }

    private static PhDTree tree3() {
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        t.insert(prof2, prof3, 1970);
        return t;
    }

    private static PhDTree tree5() {
        PhDTree t = new PhDTree(prof1, 1960);
        t.insert(prof1, prof2, 1970);
        t.insert(prof1, prof3, 1980);
        t.insert(prof1, prof4, 1990);
        t.insert(prof1, prof5, 2000);
        return t;
    }

    private static PhDTree tree6() {
        PhDTree t = new PhDTree(prof1, 2001);
        t.insert(prof1, prof2, 2002);
        t.insert(prof2, prof3, 2003);
        t.insert(prof1, prof4, 2002);
        t.insert(prof4, prof5, 2004);
        t.insert(prof4, prof6, 2005);
        return t;
    }

    @Test
    public void constructorTests() {
        assertEquals("Amy Huang", tree1().toString());
        assertEquals("Arianna Curillo", tree2().toString());
    }

    @Test
    public void numAdviseesTest1() {
        assertEquals(0, tree1().numAdvisees());
    }

    @Test
    public void numAdviseesTest2() throws NotFound {
        assertEquals(0, tree6().findTree(prof3).numAdvisees());
    }

    @Test
    public void numAdviseesTest3() throws NotFound {
        assertEquals(4, tree5().findTree(prof1).numAdvisees());
    }

    @Test
    public void sizeTest() {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        assertEquals(3, t.size());
    }

    @Test
    public void size1Test() {
        assertEquals(1, tree1().size());
    }

    @Test
    public void size2Test() {
        assertEquals(5, tree5().size());
    }

    @Test
    public void size3Test() {
        assertEquals(6, tree6().size());
    }

    @Test
    public void maxDepthTest() {
        PhDTree t = tree3();
        assertEquals(2, t.maxDepth());
    }

    @Test
    public void maxDepthTest1() {
        assertEquals(0, tree1().maxDepth());
    }

    @Test
    public void maxDepthTest2() {
        assertEquals(1, tree5().maxDepth());
    }

    @Test
    public void maxDepthTest3() {
        PhDTree t = tree1();
        t.insert(prof1, prof3, 1999);
        t.insert(prof3, prof4, 2000);
        t.insert(prof4, prof5, 2001);
        assertEquals(3, t.maxDepth());
    }


    @Test
    public void getterTests() {
        assertEquals(prof1, tree1().prof());
        // we have not inserted anything into the tree yet
        PhDTree t = new PhDTree(prof1, 2000);
        assertEquals(0, t.numAdvisees());
    }

    @Test
    public void insertTests() {
        // add professor 2 as a child of professor 1
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        t.insert(prof2, prof3, 1970);
        assertEquals(1, t.numAdvisees());
        assertEquals(3, t.size());
        assertEquals("Amy Huang[Maya Leong[Matthew Hui]]", t.toString());
    }

    @Test
    public void insertTests1() {
        PhDTree t = tree1();
        t.insert(prof1, prof2, 2000);
        assertEquals(1, t.numAdvisees());
        assertEquals(2, t.size());
        assertEquals("Amy Huang[Maya Leong]", t.toString());
    }

    @Test
    public void insertTest2() {
        PhDTree t = tree6();
        t.insert(prof2, prof7, 2010);
        assertEquals(2, t.numAdvisees());
        assertEquals(7, t.size());
        assertEquals(
                "Amy Huang[Arianna Curillo[Michelle Gao, Isa Siu], Maya Leong[Matthew Hui, Anjali Kesari]]",
                t.toString());
    }

    @Test
    public void insertTest3() {
        PhDTree t = tree5();
        t.insert(prof2, prof7, 2010);
        assertEquals(4, t.numAdvisees());
        assertEquals(6, t.size());
        PhDTree t2 = tree5();
        assertThrows(AssertionError.class, () -> t2.insert(prof3, prof4, 2010));
        assertEquals(
                "Amy Huang[Arianna Curillo, Michelle Gao, Matthew Hui, Maya Leong[Anjali Kesari]]",
                t.toString());
    }

    @Test
    public void commonAncestorTest() throws NotFound {
        PhDTree t = tree3();
        assertEquals(prof2, t.commonAncestor(prof2, prof3));
        assertEquals(prof1, t.commonAncestor(prof1, prof3));
        assertThrows(NotFound.class, () -> t.commonAncestor(prof5, prof3));
    }

    @Test
    public void commonAncestorTest1() throws NotFound {
        PhDTree t = tree5();
        t.insert(prof4, prof6, 2010);
        t.insert(prof4, prof7, 2020);
        t.insert(prof6, prof8, 2030);
        assertEquals(prof1, t.commonAncestor(prof2, prof4));
        assertEquals(prof1, t.commonAncestor(prof2, prof7));
        assertEquals(prof4, t.commonAncestor(prof6, prof7));
        assertThrows(NotFound.class, () -> t.commonAncestor(prof9, prof3));
    }

    @Test
    public void commonAncestorTest2() throws NotFound {
        PhDTree t = tree6();
        t.insert(prof3, prof7, 2010);
        t.insert(prof4, prof8, 2020);
        assertEquals(prof4, t.commonAncestor(prof5, prof8));

        assertThrows(NotFound.class, () -> t.commonAncestor(prof9, prof4));
    }

    @Test
    public void commonAncestorTest3() throws NotFound {
        PhDTree t = tree3();
        t.insert(prof3, prof4, 2010);
        t.insert(prof4, prof5, 2010);
        t.insert(prof5, prof6, 2020);
        assertEquals(prof5, t.commonAncestor(prof5, prof6));
        assertThrows(NotFound.class, () -> t.commonAncestor(prof7, prof2));
    }

    @Test
    public void toStringVerbose() {
        PhDTree t = tree3();
        String[] lines = t.toStringVerbose().split("\n");
        String[] expected = {
                "Amy Huang - 1950",
                "Maya Leong - 1960",
                "Matthew Hui - 1970"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));
    }

    @Test
    public void toStringVerbose1() {
        PhDTree t = tree1();
        String[] lines = t.toStringVerbose().split("\n");
        String[] expected = {
                "Amy Huang - 2023"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));
    }

    @Test
    public void toStringVerbose2() {
        PhDTree t = tree5();
        String[] lines = t.toStringVerbose().split("\n");
        String[] expected = {
                "Amy Huang - 1960",
                "Arianna Curillo - 1990",
                "Michelle Gao - 2000",
                "Matthew Hui - 1980",
                "Maya Leong - 1970"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));
    }

    @Test
    public void toStringVerbose3() {
        PhDTree t = tree6();
        t.insert(prof2, prof7, 2010);
        String[] lines = t.toStringVerbose().split("\n");
        String[] expected = {
                "Amy Huang - 2001",
                "Arianna Curillo - 2002",
                "Maya Leong - 2002",
                "Isa Siu - 2005",
                "Michelle Gao - 2004",
                "Anjali Kesari - 2010",
                "Matthew Hui - 2003"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));
    }

    @Test
    public void findTreeTests() throws NotFound {
        PhDTree tree1 = tree1();
        tree1.insert(prof1, prof2, 1950);
        tree1.insert(prof2, prof3, 1960);
        PhDTree tree4 = new PhDTree(prof2, 1950);
        tree4.insert(prof2, prof3, 1980);

        assertEquals(tree4.prof(), tree1.findTree(prof2).prof());
        assertEquals("Maya Leong[Matthew Hui]", tree1.findTree(prof2).toString());

        assertThrows(NotFound.class, () -> tree2().findTree(prof5));
        assertThrows(NotFound.class, () -> tree1.findTree(prof4));
        assertEquals(1, tree1.findTree(prof3).size());
    }

    @Test
    public void findTreeTest1() throws NotFound {
        PhDTree t1 = tree6();
        PhDTree t2 = new PhDTree(prof4, 2000);
        t2.insert(prof4, prof5, 2001);
        t2.insert(prof4, prof6, 2001);

        assertEquals(t2.prof(), t1.findTree(prof4).prof());
        assertEquals("Arianna Curillo[Michelle Gao, Isa Siu]", t1.findTree(prof4).toString());

        assertThrows(NotFound.class, () -> tree6().findTree(prof9));
        assertThrows(NotFound.class, () -> t2.findTree(prof3));

        assertEquals(3, t2.findTree(prof4).size());


    }

    @Test
    public void findTreeTest2() throws NotFound {
        PhDTree t1 = tree5();
        t1.insert(prof2, prof6, 2001);
        PhDTree t2 = new PhDTree(prof2, 2000);
        t2.insert(prof2, prof6, 2001);

        assertEquals(t2.prof(), t1.findTree(prof2).prof());
        assertEquals("Maya Leong[Isa Siu]", t1.findTree(prof2).toString());

        assertThrows(NotFound.class, () -> tree5().findTree(prof9));
        assertThrows(NotFound.class, () -> t2.findTree(prof3));

        assertEquals(2, t2.findTree(prof2).size());

    }

    @Test
    public void findTreeTest3() throws NotFound {
        PhDTree t1 = tree3();
        t1.insert(prof3, prof4, 2001);
        t1.insert(prof4, prof5, 2001);
        t1.insert(prof5, prof6, 2001);
        PhDTree t2 = new PhDTree(prof3, 2000);
        t2.insert(prof3, prof4, 2001);
        t2.insert(prof4, prof5, 2001);
        t2.insert(prof5, prof6, 2001);

        assertEquals(t2.prof(), t1.findTree(prof3).prof());
        assertEquals("Matthew Hui[Arianna Curillo[Michelle Gao[Isa Siu]]]",
                t1.findTree(prof3).toString());

        assertThrows(NotFound.class, () -> tree3().findTree(prof9));
        assertThrows(NotFound.class, () -> t2.findTree(prof1));
        assertEquals(4, t1.findTree(prof3).size());


    }


    @Test
    public void getAdvisorTest() throws NotFound {
        PhDTree t = tree3();
        assertEquals(prof1.toString(), t.findAdvisor(prof2).toString());
        assertThrows(NotFound.class, () -> t.findAdvisor(prof1));
    }

    @Test
    public void getAdvisorTest1() throws NotFound {
        PhDTree t = tree5();
        t.insert(prof1, prof6, 1980);
        t.insert(prof2, prof7, 1990);
        assertEquals(prof1.toString(), t.findAdvisor(prof5).toString());
        assertEquals(prof1.toString(), t.findAdvisor(prof6).toString());
        assertEquals(prof2.toString(), t.findAdvisor(prof7).toString());
        assertThrows(NotFound.class, () -> t.findAdvisor(prof9));
    }

    @Test
    public void getAdvisorTest2() throws NotFound {
        PhDTree t = tree3();
        t.insert(prof1, prof4, 1980);
        t.insert(prof2, prof5, 1990);
        t.insert(prof3, prof6, 1970);
        assertEquals(prof2.toString(), t.findAdvisor(prof5).toString());
        assertThrows(NotFound.class, () -> t.findAdvisor(prof9));
    }

    @Test
    public void getAdvisorTest3() throws NotFound {
        PhDTree t = tree5();
        assertEquals(prof1.toString(), t.findAdvisor(prof2).toString());
        assertEquals(prof1.toString(), t.findAdvisor(prof3).toString());
        assertEquals(prof1.toString(), t.findAdvisor(prof4).toString());
        assertEquals(prof1.toString(), t.findAdvisor(prof5).toString());
        assertThrows(NotFound.class, () -> t.findAdvisor(new Professor("Saanvi", "Dhaniyala")));
    }


    @Test
    public void containsTest() {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        assertTrue(t.contains(new Professor("Amy", "Huang")));
        assertFalse(t.contains(prof6));
    }

    @Test
    public void containsTest1() {
        PhDTree t = tree5();
        assertTrue(t.contains(new Professor("Michelle", "Gao")));
        assertFalse(t.contains(prof6));
        t.insert(prof5, prof6, 2000);
        assertTrue(t.contains(prof6));
        assertFalse(t.contains(prof7));
    }

    @Test
    public void containsTest2() {
        PhDTree t = tree6();
        assertTrue(t.contains(new Professor("Isa", "Siu")));
        assertTrue(t.contains(prof6));
        assertFalse(t.contains(prof7));
        assertFalse(t.contains(new Professor("Saanvi", "Dhaniyala")));
        t.insert(prof5, prof7, 2000);
        assertTrue(t.contains(prof5));
        assertTrue(t.contains(prof7));
    }

    @Test
    public void containsTest3() {
        PhDTree t = tree3();
        t.insert(prof3, prof4, 2000);
        t.insert(prof4, prof5, 2000);
        t.insert(prof5, prof6, 2000);
        assertTrue(t.contains(new Professor("Isa", "Siu")));
        assertTrue(t.contains(prof3));
        assertFalse(t.contains(prof7));
        assertFalse(t.contains(new Professor("Saanvi", "Dhaniyala")));
        assertFalse(t.contains(prof8));
        assertTrue(t.contains(prof1));
    }

    @Test
    public void findAcademicLineageTest() throws NotFound {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        List<Professor> lineage1 = new LinkedList<>();
        lineage1.add(prof1);
        lineage1.add(prof2);
        lineage1.add(prof3);
        assertEquals(lineage1, t.findAcademicLineage(prof3));
    }

    @Test
    public void findAcademicLineageTest1() throws NotFound {
        PhDTree t = tree6();
        List<Professor> l1 = new LinkedList<>();
        l1.add(prof1);
        l1.add(prof4);
        l1.add(prof5);
        assertEquals(l1, t.findAcademicLineage(prof5));
    }

    @Test
    public void findAcademicLineageTest2() throws NotFound {
        PhDTree t = tree5();
        List<Professor> l1 = new LinkedList<>();
        l1.add(prof1);
        l1.add(prof2);
        List<Professor> l2 = new LinkedList<>();
        t.insert(prof5, prof6, 2002);
        l2.add(prof1);
        l2.add(prof5);
        l2.add(prof6);
        assertEquals(l2, t.findAcademicLineage(prof6));
    }

    @Test
    public void findAcademicLineageTest3() throws NotFound {
        PhDTree t = tree6();
        List<Professor> l1 = new LinkedList<>();
        l1.add(prof1);
        l1.add(prof2);
        List<Professor> l2 = new LinkedList<>();
        t.insert(prof5, prof7, 2002);
        l2.add(prof1);
        l2.add(prof4);
        l2.add(prof5);
        l2.add(prof7);
        assertEquals(l2, t.findAcademicLineage(prof7));
    }
}
