/**
 * The AbstractAI Class serves as the parent class for concrete AI types.
 * Inside this class are shared methods and variables for all AI types.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public abstract class AbstractAI implements Player{
    protected Board board;
    protected int AIValue;
    protected int otherValue;

    /**
     *This method is an abstract constructor for subtypes of AbstractAI to use
     * @param b Board to analyze
     * @param playerNum assigned Player Number
     */
    public AbstractAI(Board b, int playerNum){
        board = b;
        setValue(playerNum);
    }

    /**
     * This method returns whether the selected Player can win the game within one move.
     * @param playerNum The selected player number.
     * @return boolean Whether the selected player can win in one move.
     */
    protected boolean isWinCase(int playerNum){
        for (int i = 0; i < Board.WIDTH; i++) {
            Board possibleBoard = new Board(board.getPieces());
            if (possibleBoard.canMakeMove(i)) {
                possibleBoard.makeMove(i, playerNum);
                if (possibleBoard.isGameOver())
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns the move needed by selected Player to end the game.
     * Returns -1 if no move can return a winning position.
     * @param playerNum The selected Player number.
     * @return int The column number that will generate a winning move.
     */
    protected int getWinCase(int playerNum){
        for (int i = 0; i < Board.WIDTH; i++) {
                Board possibleBoard = new Board(board.getPieces());
                if (possibleBoard.canMakeMove(i)) {
                    possibleBoard.makeMove(i, playerNum);
                    if (possibleBoard.isGameOver())
                        return i;
                }
        }
        return -1;
    }

    /**
     * Sets value for AI and other playe
     * @param value The assigned AI value
     */
    protected void setValue(int value){
        if (value == Board.PLAYER_1){
            AIValue = Board.PLAYER_1;
            otherValue = Board.PLAYER_2;
        }
        else{
            otherValue = Board.PLAYER_1;
            AIValue = Board.PLAYER_2;
        }
    }

    /**
     * Gets the current player number.
     * @return int Player number of AI
     */
    public int getValue(){
        return AIValue;
    }

    /**
     * Sets the AI to work on a new board
     * @param newBoard The newBoard the AI can read and interact with
     */
    public void setBoard(Board newBoard){
        board = newBoard;
    }
}
