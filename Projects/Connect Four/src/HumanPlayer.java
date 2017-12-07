import java.util.Scanner;

/**
 * The HumanPlayer Class implements the Player Interface and for use in the BoardPane Class.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class HumanPlayer implements Player {
    int playerNum;

    public HumanPlayer(int assignedNum){
        playerNum = assignedNum;
    }

    @Override
    public int chooseMove() {
        return -99;
    }

    public int getValue(){
        return playerNum;
    }
}
