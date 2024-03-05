package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ShortestPathsTest {

    /**
     * The graph example from lecture
     */
    static final String[] vertices1 = {"a", "b", "c", "d", "e", "f", "g"};
    static final String[] vertices2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    static final String[] vertices3 = {"a", "b", "c", "d", "e"};

    static final int[][] edges1 = {
            {0, 1, 9}, {0, 2, 14}, {0, 3, 15},
            {1, 4, 23},
            {2, 4, 17}, {2, 3, 5}, {2, 5, 30},
            {3, 5, 20}, {3, 6, 37},
            {4, 5, 3}, {4, 6, 20},
            {5, 6, 16}
    };

    static final int[][] edges2 = {
            {0, 1, 4},
            {1, 2, 11}, {1, 3, 9},
            {2, 0, 8},
            {3, 2, 7}, {3, 4, 2}, {3, 5, 6},
            {4, 1, 8}, {4, 6, 7}, {4, 7, 4},
            {5, 2, 1}, {5, 4, 5},
            {6, 7, 14}, {6, 8, 9},
            {7, 5, 2}, {7, 8, 10},
    };

    static final int[][] edges3 = {
            {0, 1, 10}, {0, 4, 3},
            {1, 2, 2}, {1, 4, 4},
            {2, 3, 9},
            {3, 2, 7},
            {4, 1, 1}, {4, 2, 8}, {4, 3, 2},
    };

    static class TestGraph implements WeightedDigraph<String, int[]> {

        int[][] edges;
        String[] vertices;
        Map<String, Set<int[]>> outgoing;

        TestGraph(String[] vertices, int[][] edges) {
            this.vertices = vertices;
            this.edges = edges;
            this.outgoing = new HashMap<>();
            for (String v : vertices) {
                outgoing.put(v, new HashSet<>());
            }
            for (int[] edge : edges) {
                outgoing.get(vertices[edge[0]]).add(edge);
            }
        }

        public Iterable<int[]> outgoingEdges(String vertex) {
            return outgoing.get(vertex);
        }

        public String source(int[] edge) {
            return vertices[edge[0]];
        }

        public String dest(int[] edge) {
            return vertices[edge[1]];
        }

        public double weight(int[] edge) {
            return edge[2];
        }
    }

    static TestGraph testGraph1() {
        return new TestGraph(vertices1, edges1);
    }

    static TestGraph testGraph2() {
        return new TestGraph(vertices2, edges2);
    }

    static TestGraph testGraph3() {
        return new TestGraph(vertices3, edges3);
    }

    @Test
    void lectureNotesTest() {
        TestGraph graph = testGraph1();
        ShortestPaths<String, int[]> ssp = new ShortestPaths<>(graph);
        ssp.singleSourceDistances("a");
        assertEquals(50, ssp.getDistance("g"));
        StringBuilder sb = new StringBuilder();
        sb.append("best path:");
        for (int[] e : ssp.bestPath("g")) {
            sb.append(" " + vertices1[e[0]]);
        }
        sb.append(" g");
        assertEquals("best path: a c e f g", sb.toString());
    }

    @Test
    void Test2() {
        TestGraph graph = testGraph2();
        ShortestPaths<String, int[]> ssp = new ShortestPaths<>(graph);
        ssp.singleSourceDistances("1");
        assertEquals(19, ssp.getDistance("6"));
        assertEquals(19, ssp.getDistance("8"));
        assertEquals(29, ssp.getDistance("9"));
        StringBuilder sb = new StringBuilder();
        sb.append("best path:");
        for (int[] e : ssp.bestPath("9")) {
            sb.append(" " + vertices2[e[0]]);
        }
        sb.append(" 9");
        assertEquals("best path: 1 2 4 5 8 9", sb.toString());
    }

    @Test
    void Test3() {
        TestGraph graph = testGraph3();
        ShortestPaths<String, int[]> ssp = new ShortestPaths<>(graph);
        ssp.singleSourceDistances("a");
        assertEquals(3, ssp.getDistance("e"));
        assertEquals(6, ssp.getDistance("c"));
        assertEquals(5, ssp.getDistance("d"));
        StringBuilder sb = new StringBuilder();
        sb.append("best path:");
        for (int[] e : ssp.bestPath("c")) {
            sb.append(" " + vertices3[e[0]]);
        }
        sb.append(" c");
        assertEquals("best path: a e b c", sb.toString());
    }

    // TODO: Add 2 more tests
}
