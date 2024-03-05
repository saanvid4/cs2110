package a5.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import a5.util.PlayerRole;
import org.junit.jupiter.api.Test;

class PenteTest {
    @Test
    void testConstructor() {
        Pente game1 = new Pente();
        assertEquals(game1.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game1.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        game1.board().equals(new Board(8,8));
        
        // TODO 1: write at least 1 test case
    }

    @Test
    void testCopyConstructor() {
        // test case 1: can a board state be copied to an equal state
        Pente game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        Pente game2 = new Pente(game1);
        assertTrue(game1.stateEqual(game2));

        // test case 2: same moves but different order should not have equal states
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(3, 2));
        game2 = new Pente(game1);
        Pente game3 = new Pente();
        game3.makeMove(new Position(3, 2));
        game3.makeMove(new Position(2, 2));
        Pente game4 = new Pente(game3);
        assertFalse(game2.stateEqual(game4));

        // test case 3: added moves should not be equal
        game1 = new Pente();
        game1.makeMove(new Position(1, 2));
        game2 = new Pente(game1);
        game1.makeMove(new Position(3, 5));
        game1.makeMove(new Position(6, 7));
        game1.makeMove(new Position(2,4));
        assertFalse(game1.stateEqual(game2));
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertNotEquals(game1.board(), game2.board());

        // test case 4: captured players should copy when board states are copied
        game1 = new Pente();
        game1.makeMove(new Position(1, 2));
        game2 = new Pente(game1);
        game1.makeMove(new Position(1, 3));
        game1.makeMove(new Position(1, 6));
        game1.makeMove(new Position(1,4));
        game1.makeMove(new Position(1,5));
        assertEquals(game1.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game1.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertFalse(game1.stateEqual(game2));
        assertNotEquals(game1.board(), game2.board());

        game3 = new Pente(game1);
        assertEquals(game1.board(), game3.board());
        assertTrue(game1.stateEqual(game3));
        assertEquals(game3.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game3.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        // TODO 2: write at least 3 test cases
    }



    @Test
    void testHashCode() {
        Pente game1 = new Pente();
        Pente game2 = new Pente();
        Pente game3 = new Pente();

        // test case 1: do two equal nonempty board states have the same hash code
        game1.makeMove(new Position(3, 3));
        game2.makeMove(new Position(3, 3));
        assertEquals(game1.hashCode(), game2.hashCode());

        // test case 2: non-equal board states should be very unlikely to have the
        // same hash code.
        game3.makeMove(new Position(0, 0));
        assertNotEquals(game1.hashCode(), game3.hashCode());
        
        // test case 3: hash code should be the same when using copy constructor
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(3, 3));
        game1.makeMove(new Position(4, 4));
        game1.makeMove(new Position(5,5));
        game1.makeMove(new Position(6, 6));
        game2 = new Pente(game1);
        assertEquals(game1.hashCode(), game2.hashCode());

        // test case 4: hash codes should not be equal when same moves but different order
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(4, 3));
        game1.makeMove(new Position(6, 5));
        game1.makeMove(new Position(2,1));
        game1.makeMove(new Position(6, 0));
        game2 = new Pente();
        game2.makeMove(new Position(2, 2));
        game2.makeMove(new Position(4, 3));
        game2.makeMove(new Position(6, 5));
        game2.makeMove(new Position(6,0));
        game2.makeMove(new Position(2, 1));
        assertNotEquals(game1.hashCode(), game2.hashCode());

        // test case 5: two different board with same moves and captured players should have same
        // hash code
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(3, 3));
        game1.makeMove(new Position(6, 5));
        game1.makeMove(new Position(4,4));
        game1.makeMove(new Position(5, 5));
        game2 = new Pente();
        game2.makeMove(new Position(2, 2));
        game2.makeMove(new Position(3, 3));
        game2.makeMove(new Position(6, 5));
        game2.makeMove(new Position(4,4));
        game2.makeMove(new Position(5, 5));
        assertEquals(game1.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game1.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertEquals(game1.hashCode(), game2.hashCode());
        // TODO 3: write at least 3 test cases
    }

    @Test
    void makeMove() {
        // test case 1: a simple move
        Pente game = new Pente();
        Position p = new Position(2, 2);
        game.makeMove(p); // a move by the first player
        assertEquals(PlayerRole.SECOND_PLAYER, game.currentPlayer());
        assertEquals(2, game.currentTurn());
        assertFalse(game.hasEnded());
        assertEquals(0, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.capturedPairsNo(PlayerRole.SECOND_PLAYER));
        assertEquals(PlayerRole.FIRST_PLAYER.boardValue(), game.board().get(p));

        // test case 2: try a capture
        game.makeMove(new Position(2, 3)); // a move by the second player
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(2, 4)); // a move by the second player
        game.makeMove(new Position(2, 5)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(1, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.board().get(new Position(2, 3))); // the stone should be removed
        assertEquals(0, game.board().get(new Position(2, 4))); // the stone should be removed

        // test case 3: try to move outside the board
        game = new Pente();
        game.makeMove(new Position(5, 7)); // a move by the first player
        game.makeMove(new Position(8, 8)); // a move by the second player
        game.makeMove(new Position(3, 8)); // a move by the first player
        Pente game2 = new Pente(game);
        game.makeMove(new Position(8, 9));
        game.makeMove(new Position(9, 10));
        assertTrue(game.stateEqual(game2));

        // test case 4: try to move to a position with a piece already there
        game = new Pente();
        game.makeMove(new Position(1, 1)); // a move by the first player
        game.makeMove(new Position(4, 4)); // a move by the second player
        game.makeMove(new Position(4, 4)); // a move by the first player

        game2 = new Pente(game);
        game.makeMove(new Position(1, 1)); // a move by the first player
        assertTrue(game.stateEqual(game2));

        // test case 5: does an invalid move change the player
        game = new Pente();
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(9, 10));
        game.makeMove(new Position(3, 2)); // a move by the second player
        game.makeMove(new Position(2, 1)); // a move by the first player
        game2 = new Pente();
        game2.makeMove(new Position(3, 2)); // a move by the first player
        game2.makeMove(new Position(3, 2)); // a move by the second player
        game2.makeMove(new Position(2, 1)); // a move by the first player
        assertTrue(game.stateEqual(game2));

        // TODO 4: write at least 3 test cases
    }

    @Test
    void capturedPairsNo() {
        // test case 1: are captured pairs registered?
        Pente game = new Pente();
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(3, 3)); // a move by the second player
        game.makeMove(new Position(4, 2)); // a move by the first player
        game.makeMove(new Position(3, 4)); // a move by the second player
        game.makeMove(new Position(3, 5)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(1, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.capturedPairsNo(PlayerRole.SECOND_PLAYER));

        // test case 2: should not self sabotage
        game = new Pente();
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(4, 2)); // a move by the second player
        game.makeMove(new Position(6, 2)); // a move by the first player
        game.makeMove(new Position(5, 2)); // a move by the second player
        assertEquals(0, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.capturedPairsNo(PlayerRole.SECOND_PLAYER));

        // test case 3: should not capture if move is invalid
        game = new Pente();
        game.makeMove(new Position(8, 8)); // a move by the first player
        game.makeMove(new Position(8, 6)); // a move by the second player
        game.makeMove(new Position(8, 7)); // a move by the first player
        game.makeMove(new Position(8, 9)); // a move by the second player
        assertEquals(0, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.capturedPairsNo(PlayerRole.SECOND_PLAYER));

        // test case 4: should be able to capture two pairs
        game = new Pente();
        game.makeMove(new Position(1, 1)); // a move by the first player
        game.makeMove(new Position(2, 1)); // a move by the second player
        game.makeMove(new Position(4, 4)); // a move by the first player
        game.makeMove(new Position(4, 3)); // a move by the second player
        game.makeMove(new Position(6, 7)); // a move by the first player
        game.makeMove(new Position(4, 2)); // a move by the second player
        game.makeMove(new Position(1, 8)); // a move by the first player
        game.makeMove(new Position(3, 1)); // a move by the second player
        game.makeMove(new Position(8, 1)); // a move by the first player
        game.makeMove(new Position(4, 1)); // a move by the second player


        assertEquals(0, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.capturedPairsNo(PlayerRole.SECOND_PLAYER));

        // TODO 5: write at least 3 test cases
    }

    @Test
    void hasEnded() {
        // test case 1: is a board with 5 in a row an ended game?
        Pente game = new Pente();
        assertFalse(game.hasEnded());
        game.makeMove(new Position(1, 1)); // a move by the first player
        game.makeMove(new Position(2, 1)); // a move by the second player
        game.makeMove(new Position(1, 2)); // a move by the first player
        game.makeMove(new Position(2, 2)); // a move by the second player
        game.makeMove(new Position(1, 3)); // a move by the first player
        game.makeMove(new Position(2, 3)); // a move by the second player
        game.makeMove(new Position(1, 4)); // a move by the first player
        game.makeMove(new Position(2, 4)); // a move by the second player
        game.makeMove(new Position(1, 5)); // a move by the first player
        assertTrue(game.hasEnded());

        // test case 2: is a full board an ended game?
        game = new Pente();
        assertFalse(game.hasEnded());
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                game.makeMove(new Position(i, j)); // a move by first and second player

            }
        }
        System.out.println();

        assertTrue(game.hasEnded());

        // test case 3: is a board with 5 in a diagonal an ended game?
        game = new Pente();
        assertFalse(game.hasEnded());
        game.makeMove(new Position(0, 0)); // a move by the first player
        game.makeMove(new Position(1, 1)); // a move by the second player
        game.makeMove(new Position(1, 2)); // a move by the first player
        game.makeMove(new Position(2, 2)); // a move by the second player
        game.makeMove(new Position(1, 3)); // a move by the first player
        game.makeMove(new Position(3, 3)); // a move by the second player
        game.makeMove(new Position(5, 4)); // a move by the first player
        game.makeMove(new Position(4, 4)); // a move by the second player
        game.makeMove(new Position(6, 5)); // a move by the first player
        game.makeMove(new Position(5, 5)); // a move by the first player
        assertTrue(game.hasEnded());
        // test case 4: is a board with 5 in a column an ended game?

        game = new Pente();
        assertFalse(game.hasEnded());
        game.makeMove(new Position(1, 1)); // a move by the first player
        game.makeMove(new Position(1, 2)); // a move by the second player
        game.makeMove(new Position(2, 1)); // a move by the first player
        game.makeMove(new Position(2, 2)); // a move by the second player
        game.makeMove(new Position(3, 1)); // a move by the first player
        game.makeMove(new Position(3, 2)); // a move by the second player
        game.makeMove(new Position(4, 1)); // a move by the first player
        game.makeMove(new Position(4, 2)); // a move by the second player
        game.makeMove(new Position(5, 1)); // a move by the first player
        assertTrue(game.hasEnded());
    }
    @Test
    void stateEqual() {
        Pente game1 = new Pente();
        Pente game2 = new Pente();
        Pente game3 = new Pente();

        // test 1: games with equal board states should be stateEqual()
        game1.makeMove(new Position(3, 3));
        game2.makeMove(new Position(3, 3));
        assertTrue(game1.stateEqual(game2));
        assertTrue(game2.stateEqual(game1));

        // test 2: games with unequal board states should not be stateEqual()
        game3.makeMove(new Position(0, 0));
        assertFalse(game1.stateEqual(game3));

        // test 3: games created with copy constructor should be stateEqual()
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(3, 3));
        game1.makeMove(new Position(4, 4));
        game1.makeMove(new Position(5,5));
        game1.makeMove(new Position(6, 6));
        game2 = new Pente(game1);
        assertTrue(game1.stateEqual(game2));

        // test case 4: same moves but different order should not be stateEqual()
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(4, 3));
        game1.makeMove(new Position(6, 5));
        game1.makeMove(new Position(2,1));
        game1.makeMove(new Position(6, 0));
        game2 = new Pente();
        game2.makeMove(new Position(2, 2));
        game2.makeMove(new Position(4, 3));
        game2.makeMove(new Position(6, 5));
        game2.makeMove(new Position(6,0));
        game2.makeMove(new Position(2, 1));
        assertFalse(game1.stateEqual(game2));

        // test case 5: two different board with same moves and captured players should be
        // stateEqual()
        game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        game1.makeMove(new Position(3, 3));
        game1.makeMove(new Position(6, 5));
        game1.makeMove(new Position(4,4));
        game1.makeMove(new Position(5, 5));
        game2 = new Pente();
        game2.makeMove(new Position(2, 2));
        game2.makeMove(new Position(3, 3));
        game2.makeMove(new Position(6, 5));
        game2.makeMove(new Position(4,4));
        game2.makeMove(new Position(5, 5));
        assertEquals(game1.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game1.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertTrue(game1.stateEqual(game2));
        // TODO 7: write at least 3 test cases
    }
}