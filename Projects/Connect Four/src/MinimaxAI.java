/**
 * The MinimaxAI uses the Minimax algorithm defined in the Minimax class to choose a move.
 * MinimaxAI is a subclass of AbstractAI.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class MinimaxAI extends AbstractAI {
    private int maxDepth;

    /**
     * This is a constructor for Minimax AI. Sets the board and player number in a super call.
     * Keeps maxDepth as a field.
     * @param b Board to be read.
     * @param playerNum Player number.
     * @param maxDepth Maximum depth called on alphaBeta methos
     */
    public MinimaxAI(Board b, int playerNum, int maxDepth){
        super(b, playerNum);
        this.maxDepth = maxDepth;
    }

    /**
     * This method returns the chosen move for the AI.
     * First, it checks if the AI can win, and if so, makes that move.
     * Secondly, it checks if the opposing player can win, and if so, makes that move.
     * Finally, it calls the minimax method using the maximum depth
     * to determine the best move to make in the absence of those two cases.
     * @return int The best move using the above constraints.
     */
    public int chooseMove() {
        if (isWinCase(AIValue))
            return getWinCase(AIValue);
        else if (isWinCase(otherValue))
            return getWinCase(otherValue);
        return Minimax.minimax(board, maxDepth, AIValue)[1];
    }
}
