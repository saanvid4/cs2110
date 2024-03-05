package a5.ai;

import static org.junit.jupiter.api.Assertions.*;

import a5.ai.TranspositionTable.StateInfo;
import a5.logic.Position;
import a5.logic.TicTacToe;
import cms.util.maybe.Maybe;
import cms.util.maybe.NoMaybeValue;
import org.junit.jupiter.api.Test;

class TranspositionTableTest {

    @Test
    void testConstructor() {
        TranspositionTable t = new TranspositionTable();
        assertEquals(0, t.size());
        // TODO 1: write at least 1 test case
    }


    @Test
    void getInfo() throws NoMaybeValue {
        // test case 1: look for a state that is in the table
        TranspositionTable<TicTacToe> table = new TranspositionTable<>();
        TicTacToe state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);

        StateInfo info = table.getInfo(state).get();
        assertEquals(GameModel.WIN, info.value());
        assertEquals(0, info.depth());

        // test case 2: look for a state not in the table
        TicTacToe state2 = state.applyMove(new Position(0, 0));
        assertThrows(NoMaybeValue.class, () -> table.getInfo(state2).get());

        //test case 3: return updated occurence of state if new arguments sent
        table.add(state, 6, 5);
        StateInfo info1 = table.getInfo(state).get();
        assertEquals(6, info1.depth());
        assertEquals(5, info1.value());

        //test case 4: return none when there is no value in the table that matches that state
        TranspositionTable<TicTacToe> table2 = new TranspositionTable<>();
        assertEquals(Maybe.none(), table2.getInfo(state));
        assertThrows(NoMaybeValue.class, () -> table2.getInfo(state2).get());

        //test case 5: return depth and value of a state when there are multiple states
        table.add(state2, 0, 0);
        TicTacToe state3 = state.applyMove(new Position(1, 0));
        table.add(state3, 1, 1);
        StateInfo info2 = table.getInfo(state).get();
        assertEquals(6, info2.depth());
        assertEquals(5, info2.value());
        StateInfo info3 = table.getInfo(state2).get();
        assertEquals(0, info3.depth());
        assertEquals(0, info3.value());
        StateInfo info4 = table.getInfo(state3).get();
        assertEquals(1, info4.depth());
        assertEquals(1, info4.value());

        // TODO 2: write at least 3 more test cases
    }

    @Test
    void add() throws NoMaybeValue {
        TranspositionTable<TicTacToe> table = new TranspositionTable<>();

        // test case 1: add a state and check it is in there
        TicTacToe state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);

        StateInfo info = table.getInfo(state).get();
        assertEquals(GameModel.WIN, info.value());
        assertEquals(0, info.depth());

        // test case 2: add a repeat state and check depth and value
        TranspositionTable<TicTacToe> table2 = new TranspositionTable<>();
        TicTacToe state2 = new TicTacToe();
        table2.add(state2, 0, 1);
        TicTacToe state3 = state2.applyMove(new Position(1, 0));
        table.add(state3, 1, 0);
        info = table.getInfo(state3).get();
        assertEquals(0, info.value());
        assertEquals(1, info.depth());

        // test case 3: test size when adding multiple states
        TranspositionTable<TicTacToe> table3 = new TranspositionTable<>();
        state = new TicTacToe();
        table3.add(state, 0, 2);
        state3 = state2.applyMove(new Position(1, 2));
        table3.add(state3, 1, 2);
        TicTacToe state4 = state3.applyMove(new Position(2, 0));
        table3.add(state4, 2, 2);
        assertEquals(3, table3.size());

        // test case 4: edge of board should add correctly
        table = new TranspositionTable<>();
        state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);
        state2 = state.applyMove(new Position(0, 0));
        table.add(state2, 0, 0);
        state3 = state.applyMove(new Position(8, 1));
        table.add(state3, 0, 0);

        info = table.getInfo(state).get();
        assertEquals(10000, info.value());
        assertEquals(0, info.depth());
        assertEquals(2, table.size());
        // TODO 3: write at least 3 more test cases
    }

}
