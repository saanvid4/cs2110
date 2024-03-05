package a5.logic;

import a5.util.PlayerRole;
import a5.util.GameType;
import a5.util.GameResult;
import java.util.Arrays;


/**
 * A Pente game, where players take turns to place stones on board.
 * When consecutive two stones are surrounded by the opponent's stones on two ends,
 * these two stones are removed (captured).
 * A player wins by placing 5 consecutive stones or capturing stones 5 times.
 */
public class Pente extends MNKGame {
    private int numPiecesCaptured1;
    private int numPiecesCaptured2;


    /**
     * Create an 8-by-8 Pente game.
     */
    public Pente() {
        super(8, 8, 5);

        // TODO 1
        numPiecesCaptured1=0;
        numPiecesCaptured2=0;

    }

    /**
     * Creates: a copy of the game state.
     */
    public Pente(Pente game) {
        super(game);
        // TODO 2
        numPiecesCaptured1= game.numPiecesCaptured1;
        numPiecesCaptured2= game.numPiecesCaptured2;
    }

    @Override
    public boolean makeMove(Position p) {
        // TODO 3
        if (!board().validPos(p)) {
            return false;
        }
        board().place(p, currentPlayer());
        checkCapture(p);
        changePlayer();
        advanceTurn();
        return true;
    }
    public void checkCapture(Position p){

        Position p1,p2,p3;
        for(int i=0; i<9;i++){
            switch(i){
                case 0:
                    p1= new Position(p.row()-3,p.col()+3);
                    p2= new Position(p.row()-2,p.col()+2);
                    p3=new Position(p.row()-1,p.col()+1);
                    break;
                case 1:
                    p1= new Position(p.row()-3,p.col());
                    p2= new Position(p.row()-2,p.col());
                    p3=new Position(p.row()-1,p.col());
                    break;
                case 2:
                    p1= new Position(p.row()-3,p.col()-3);
                    p2= new Position(p.row()-2,p.col()-2);
                    p3=new Position(p.row()-1,p.col()-1);
                    break;
                case 3:
                    p1= new Position(p.row(),p.col()+3);
                    p2= new Position(p.row(),p.col()+2);
                    p3=new Position(p.row(),p.col()+1);
                    break;
                case 5:
                    p1= new Position(p.row(),p.col()-3);
                    p2= new Position(p.row(),p.col()-2);
                    p3=new Position(p.row(),p.col()-1);
                    break;
                case 6:
                    p1= new Position(p.row()+3,p.col()+3);
                    p2= new Position(p.row()+2,p.col()+2);
                    p3=new Position(p.row()+1,p.col()+1);
                    break;
                case 7:
                    p1= new Position(p.row()+3,p.col());
                    p2= new Position(p.row()+2,p.col());
                    p3=new Position(p.row()+1,p.col());
                    break;
                case 8:
                    p1= new Position(p.row()+3,p.col()-3);
                    p2= new Position(p.row()+2,p.col()-2);
                    p3=new Position(p.row()+1,p.col()-1);
                    break;
                default:
                    continue;
            }
            if(p1.row()>=0&&p1.col()>=0){

                if(board().onBoard(p1)){
                    if(board().get(p1)==currentPlayer().boardValue()&&board().get(p2)==currentPlayer().nextPlayer().boardValue()&&board().get(p3)==currentPlayer().nextPlayer().boardValue()) {
                        board().erase(p2);
                        board().erase(p3);
                        if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                            numPiecesCaptured1+=2;
                        }else{
                            numPiecesCaptured2+=2;
                        }
                    }

                }

            }
        }
    }

    /**
     * Returns: a new game state representing the state of the game after the current player takes a
     * move {@code p}.
     */
    public Pente applyMove(Position p) {
        Pente newGame = new Pente(this);
        newGame.makeMove(p);
        return newGame;
    }

    /**
     * Returns: the number of captured pairs by {@code playerRole}.
     */
    public int capturedPairsNo(PlayerRole playerRole) {
        // TODO 4
        if (playerRole.equals(PlayerRole.FIRST_PLAYER)){
            return numPiecesCaptured1/2;
        }
        return numPiecesCaptured2/2;
    }

    @Override
    public boolean hasEnded() {
        // TODO 5
        return super.hasEnded()&&(numPiecesCaptured1<countToWin())&&(numPiecesCaptured2<countToWin());
    }

    @Override
    public GameType gameType() {
        return GameType.PENTE;
    }


    @Override
    public String toString() {
        String board = super.toString();
        return board + System.lineSeparator() + "Captured pairs: " +
                "first: " + capturedPairsNo(PlayerRole.FIRST_PLAYER) + ", " +
                "second: " + capturedPairsNo(PlayerRole.SECOND_PLAYER);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Pente p = (Pente) o;
        return stateEqual(p);
    }

    /**
     * Returns: true if the two games have the same state.
     */
    protected boolean stateEqual(Pente p) {
        // TODO 6
        return (board().equals(p.board())&&currentPlayer().equals(p.currentPlayer())&&numPiecesCaptured1==p.numPiecesCaptured1&&numPiecesCaptured2==p.numPiecesCaptured2);
    }

    @Override
    public int hashCode() {
        // TODO 7
        return Arrays.hashCode(new int[]{
                board().hashCode(),
                countToWin(),
                numPiecesCaptured1,
                numPiecesCaptured2
        });
    }
}

