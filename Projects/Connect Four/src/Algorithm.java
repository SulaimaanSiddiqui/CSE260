import java.util.Random;

/**
 * The Algorithm class is the abstract class for the minimax and alphabeta classes.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public abstract class Algorithm {
    private static final Random r = new Random(666);

    /**
     * This method return a random boolean.
     * @return boolean A random boolean
     */
    protected static boolean getRandom(){
        return r.nextBoolean();
    }

    protected static boolean isLosingMove(Board b, int col, int player){
        Board newBoard = new Board(b.getPieces());
        newBoard.makeMove(col, player);
        if (player == Board.PLAYER_1){
            for (Board possible: Board.getPossibleBoards(newBoard, Board.PLAYER_2)) {
                if (possible.getWinner() == Board.PLAYER_2)
                return true;
            }
        }
        else {
            for (Board possible: Board.getPossibleBoards(newBoard, Board.PLAYER_1)) {
                if (possible.getWinner() == Board.PLAYER_1)
                    return true;
            }
        }
        return false;
    }

}
