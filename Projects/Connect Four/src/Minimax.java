import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The Minimax implements the minimax algorithm and return
 * it using the static minimax method for use in the MinimaxAI
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class Minimax extends Algorithm{

    /**
     * This method uses an minimax algorithm to determine the best
     * move in a game of connect four.
     * @param board The Board to be read
     * @param depth The depth with which the algorithm is testing up till
     * @param playerNum The current player
     * @return
     */
    public static int[] minimax(Board board, int depth, int playerNum){
        //if the depth is 0 or the game is over return the last move
        if(depth == 0 || board.isGameOver()){
            return board.getLastMove();
        }
        //If player 1, maximize board score
        else if (playerNum == Board.PLAYER_1){
            int[] bestMove = new int[3];
            bestMove[2] = Integer.MIN_VALUE;
            //check nodes of next tree
            for (Board b : Board.getPossibleBoards(board, Board.PLAYER_2)) {
                //calls minimax using next player to determine minimum board score possible
                int[] move = minimax(b, depth - 1, Board.PLAYER_2);
                if (move[2] > bestMove[2]){
                    bestMove = move;
                }
                if(move[2] == bestMove[2]){
                    if (Math.abs(move[1] - 3) < Math.abs(bestMove[1] - 3)){
                        bestMove = move;
                    }
                    if (getRandom()){
                        bestMove = move;
                    }
                }
            }
            //maximizes the minimum loss
            return bestMove;
        }
        //if player 2, minimize board score
        else if (playerNum == 2){
            int[] bestMove = new int[3];
            bestMove[2] = Integer.MAX_VALUE;
            //check next nodes of tree
            for (Board b : Board.getPossibleBoards(board, 1)) {
                //determine maximum loss using recursion
                int[] move = minimax(b, depth - 1, 1);
                if (move[2] < bestMove[2]){
                    bestMove = move;
                }
                if(move[2] == bestMove[2]){
                    if (Math.abs(move[1] - 3) < Math.abs(bestMove[1] - 3)){
                        bestMove = move;
                    }
                    if (getRandom()){
                        bestMove = move;
                    }
                }
            }
            //minimizes max loss
            return bestMove;
        }
        return null;
    }
}
