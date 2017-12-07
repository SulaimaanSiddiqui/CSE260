/**
 * The SimpleAI uses a simple algorithm defined in this class to choose a move.
 * SimpleAI is a subclass of AbstractAI.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class SimpleAI extends AbstractAI {

    public SimpleAI(Board b, int playerNum){
        super(b, playerNum);
    }

    public int chooseMove() {
        if(isWinCase(AIValue))
            return getWinCase(AIValue);
        else if (isWinCase(otherValue))
            return getWinCase(otherValue);
        else {
            int col = -1;
            do {
                col = (int) (Math.random() * Board.WIDTH);

            } while (!board.canMakeMove(col));
            return col;
        }
    }
}
