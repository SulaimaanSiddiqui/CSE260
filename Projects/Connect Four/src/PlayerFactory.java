/**
 * The PlayerFactory class is a factory class used to create types of Players
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class PlayerFactory {



    public static Player getPlayer(String player, int playerNum, Board b, int maxDepth){
        switch (player){
            case "Human":
                return new HumanPlayer(playerNum);
            case "Simple AI":
                return new SimpleAI(b, playerNum);
            case "Minimax AI":
                return new MinimaxAI(b, playerNum, maxDepth);
            case "Alpha Beta AI":
                return new AlphaBetaAI(b, playerNum, maxDepth);
        }
        return null;
    }
}
