package a5.ai;

import cms.util.maybe.Maybe;
import cms.util.maybe.NoMaybeValue;

/**
 * A transposition table for an arbitrary game. It maps a game state
 * to a search depth and a heuristic evaluation of that state to the
 * recorded depth. Unlike a conventional map abstraction, a state is
 * associated with a depth, so that clients can look for states whose
 * entry has at least the desired depth.
 *
 * @param <GameState> A type representing the state of a game.
 */
public class TranspositionTable<GameState> {

    /**
     * Information about a game state, for use by clients.
     */
    public interface StateInfo {

        /**
         * The heuristic value of this game state.
         */
        int value();

        /**
         * The depth to which the game tree was searched to determine the value.
         */
        int depth();
    }

    /**
     * A Node is a node in a linked list of nodes for a chaining-based implementation of a hash
     * table.
     *
     * @param <GameState>
     */
    static private class Node<GameState> implements StateInfo {
        /**
         * The state
         */
        GameState state;
        /**
         * The depth of this entry. >= 0
         */
        int depth;
        /**
         * The value of this entry.
         */
        int value;
        /**
         * The next node in the list. May be null.
         */
        Node<GameState> next;

        Node(GameState state, int depth, int value, Node<GameState> next) {
            this.state = state;
            this.depth = depth;
            this.value = value;
            this.next = next;
        }

        public int value() {
            return value;
        }

        public int depth() {
            return depth;
        }
    }

    /**
     * The number of entries in the transposition table.
     */
    private int size;

    /**
     * The buckets array may contain null elements.
     * Class invariant:
     * All transposition table entries are found in the linked list of the
     * bucket to which they hash, and the load factor is no more than 1.
     */
    private Node<GameState>[] buckets;

    // TODO 1: implement the classInv() method. You may also
    // strengthen the class invariant. The classInv()
    // method is likely to be expensive, so you may want to turn
    // off assertions in this file, but only after you have the transposition
    // table fully tested and working.
    boolean classInv() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                Node<GameState> loop = buckets[i];
                while(loop != null) {
                    if (Math.abs(loop.state.hashCode()) % buckets.length != i)
                        return false;
                    loop = loop.next;
                }
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    /** Creates: a new, empty transposition table. */
    TranspositionTable() {
        size = 0;
        // TODO 2
        buckets = new Node[7];
        assert classInv();
    }

    /** The number of entries in the transposition table. */
    public int size() {
        assert classInv();
        return size;
    }

    /**
     * Returns: the information in the transposition table for a given
     * game state, package in an Optional. If there is no information in
     * the table for this state, returns an empty Optional.
     */
    public Maybe<StateInfo> getInfo(GameState state) {
        // TODO 3
        assert classInv();
        if(this.size == 0) return Maybe.none();
        int index = Math.abs(state.hashCode()%this.buckets.length);
        Node<GameState> node = this.buckets[index];
        while (node != null) {
            if (node.state.equals(state))
                return Maybe.some(node);
            node = node.next;
        }
        return Maybe.none();
    }

    /**
     * Effect: Add a new entry in the transposition table for a given
     * state and depth, or overwrite the existing entry for this state
     * with the new state and depth. Requires: if overwriting an
     * existing entry, the new depth must be greater than the old one.
     */
    public void add(GameState state, int depth, int value) {
        // TODO 4
        assert classInv();
        if((this.size + 1.0)/this.buckets.length > 1.0) {
            this.grow(this.size*2);
        }
        int index = Math.abs(state.hashCode()%this.buckets.length);
        Node<GameState> node = new Node<GameState>(state, depth, value, null);
        if(this.buckets[index] == null) {
            this.buckets[index] = node;
            this.size++;
            return;
        }
        node = this.buckets[index];
        if(node.state.equals(state)) {
            if(node.depth < depth) {
                node.depth = depth;
                node.value = value;
            }
            return;
        }
        while (node.next != null) {
            if(node.next.state.equals(state)) {
                if(node.next.depth < depth) {
                    node.next.depth = depth;
                    node.next.value = value;
                }
                return;
            }
            node = node.next;
        }
        node.next = new Node<GameState>(state, depth, value, null);
        this.size++;
    }

    /**
     * Effect: Make sure the hash table has at least {@code target} buckets.
     * Returns true if the hash table actually resized.
     */
    private boolean grow(int target) {
        // TODO 5
        assert classInv();
        if(this.buckets.length >= target) return false;
        Node<GameState>[] temp = this.buckets;
        this.buckets = new Node[target];
        for(Node<GameState> bucket : temp) {
            while (bucket != null) {
                this.add(bucket.state, bucket.depth(), bucket.value());
                bucket = bucket.next;
            }
        }
        assert classInv();
        return true;
    }

    // You may want to write some additional helper methods.

    /**
     * Estimate clustering. With a good hash function, clustering
     * should be around 1.0. Higher values of clustering lead to worse
     * performance.
     */
    double estimateClustering() {
        final int N = 500;
        int m = buckets.length, n = size;
        double sum2 = 0;
        for (int i = 0; i < N; i++) {
            int j = Math.abs((i * 82728353) % buckets.length);
            int count = 0;
            Node<GameState> node = buckets[j];
            while (node != null) {
                count++;
                node = node.next;
            }
            sum2 += count*count;
        }
        double alpha = (double)n/m;
        return sum2/(N * alpha * (1 - 1.0/m + alpha));
    }

    public static final int EXACT_CUTOFF = 500;
    double estimateClustering(boolean exact) {
        int m = buckets.length, n = size;
        if (buckets.length < EXACT_CUTOFF) exact = true;
        final int N = exact ? m : EXACT_CUTOFF;
        double sum2 = 0;
        for (int i = 0; i < N; i++) {
            int j = exact ? i : Math.abs((i * 82728353) % buckets.length);
            int count = 0;
            Node<GameState> node = buckets[j];
            while (node != null) {
                count++;
                node = node.next;
            }
            sum2 += count*count;
        }
        double alpha = (double)n/m;
        return sum2/(N * alpha * (1 - 1.0/m + alpha));
    }
}
