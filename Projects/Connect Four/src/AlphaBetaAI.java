/**
 * The AlphaBetaAI uses the AlphaBeta algorithm defined in the AlphaBeta class to choose a move.
 * AlphaBetaAI is a subclass of AbstractAI.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class AlphaBetaAI extends AbstractAI{
    private int maxDepth;

    /**
     * This is a constructor for AlphaBeta AI. Sets the board and player number in a super call.
     * Keeps maxDepth as a field.
     * @param b Board to be read.
     * @param playerNum Player number.
     * @param maxDepth Maximum depth called on alphaBeta methos
     */
    public AlphaBetaAI(Board b, int playerNum, int maxDepth){
        super(b, playerNum);
        this.maxDepth = maxDepth;
    }

    /**
     * This method returns the chosen move for the AI.
     * First, it checks if the AI can win, and if so, makes that move.
     * Secondly, it checks if the opposing player can win, and if so, makes that move.
     * Finally, it calls the alphabeta method using the maximum depth
     * to determine the best move to make in the absence of those two cases.
     * @return int The best move using the above constraints.
     */
    public int chooseMove() {
        if (isWinCase(AIValue))
            return getWinCase(AIValue);
        else if (isWinCase(otherValue)){
            return getWinCase(otherValue);}
        return AlphaBeta.alphaBeta(board, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, AIValue)[1];
    }
}
