package a5.logic;

import static org.junit.jupiter.api.Assertions.*;

import a5.util.PlayerRole;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testEquals() {
        Board board1 = new Board(3, 3);
        Board board2 = new Board(3, 3);
        Board board3 = new Board(3, 3);
        Board board4 = new Board(3, 3);
        // test 1: empty boards should be equal
        assertEquals(board1, board2);

        // test 2: adding a piece should break equality
        board2.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board1, board2);

        // test 3: erasing a piece should break equality
        board1.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertEquals(board1, board2);
        board2.erase(new Position(0, 0));
        assertNotEquals(board1, board2);
        // test 4: two same pieces but different players should not be equal
        board1.place(new Position(0,0), PlayerRole.SECOND_PLAYER);
        board1.place(new Position(0,1), PlayerRole.SECOND_PLAYER);
        board2.place(new Position(0,0), PlayerRole.FIRST_PLAYER);
        board2.place(new Position(1,0), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board1, board2);
        // test 5: same pieces same players should be equal
        board3.place(new Position(1,2), PlayerRole.FIRST_PLAYER);
        board3.place(new Position(2,2), PlayerRole.SECOND_PLAYER);
        board4.place(new Position(2,2), PlayerRole.SECOND_PLAYER);
        board4.place(new Position(1,2), PlayerRole.FIRST_PLAYER);
        assertEquals(board3, board4);
        // TODO 1: write at least 3 test cases
    }

    @Test
    void testHashCode() {
        Board board1 = new Board(3, 3);
        Board board2 = new Board(3, 3);
        Board board3 = new Board(3, 3);
        Board board4 = new Board(3, 3);

        // test 1: equal boards should have the same hash code
        assertEquals(board1.hashCode(), board2.hashCode());

        // test 2: unequal boards should be very unlikely to have the
        // same hash code
        board2.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board1.hashCode(), board2.hashCode());

        // test 3: erasing a piece should not have the same hashcode
        board1.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertEquals(board1, board2);
        board2.erase(new Position(0, 0));
        assertNotEquals(board1.hashCode(), board2.hashCode());
        // test 4: piece in same spot but different players
        board2.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertEquals(board1, board2);
        board2.place(new Position(0, 0), PlayerRole.SECOND_PLAYER);
        assertNotEquals(board1.hashCode(), board2.hashCode());
        // test 5: piece in same spot but same players
        board3.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertEquals(board1, board3);
        board4.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertEquals(board3.hashCode(), board4.hashCode());
        assertEquals(board1.hashCode(), board3.hashCode());
        assertEquals(board1.hashCode(), board4.hashCode());
        // TODO 2: write at least 3 test cases
    }

    @Test
    void get() {
        Board board = new Board(5, 5);
        Position p = new Position(0, 0);
        // test 1: Check that an empty space reports 0
        assertEquals(0, board.get(p));

        // test 2: Check that a placed piece is seen by get()
        board.place(p, PlayerRole.FIRST_PLAYER);
        assertEquals(PlayerRole.FIRST_PLAYER.boardValue(), board.get(p));
    }

    @Test
    void place() {
        // test 1: do placed pieces show up where they are placed?
        Board board = new Board(5, 5);
        Position p = new Position(0, 0);
        assertEquals(0, board.get(p));
        board.place(p, PlayerRole.SECOND_PLAYER);
        assertEquals(PlayerRole.SECOND_PLAYER.boardValue(), board.get(p));
    }

    @Test
    void erase() {
        Board board = new Board(5, 5);
        Position p = new Position(0, 0);
        board.place(p, PlayerRole.SECOND_PLAYER);

        // test 1: do pieces get erased?
        board.erase(p);
        assertEquals(0, board.get(p));
    }

    @Test
    void rowSize() {
        Board board = new Board(5, 6);
        assertEquals(5, board.rowSize());
    }

    @Test
    void colSize() {
        Board board = new Board(5, 6);
        assertEquals(6, board.colSize());
    }

    @Test
    void validPos() {
        Board board = new Board(5, 6);
        Position p = new Position(3, 3);
        // test 1: is a valid position valid?
        assertTrue(board.validPos(p));
        board.place(p, PlayerRole.FIRST_PLAYER);

        // test 2: is an invalid position invalid?
        p = new Position(5, 5);
        assertFalse(board.validPos(p));
    }

    @Test
    void onBoard() {
        Board board = new Board(5, 6);
        Position p = new Position(3, 3);
        // test 1: is a valid empty position on board?
        assertTrue(board.onBoard(p));
        // test 2: is a valid nonempty position on board?
        board.place(p, PlayerRole.FIRST_PLAYER);
        assertTrue(board.onBoard(p));
        // test 3: is an invalid position on board?
        p = new Position(5, 5);
        assertFalse(board.onBoard(p));
    }
}