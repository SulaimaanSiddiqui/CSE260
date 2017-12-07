import java.util.ArrayList;
import java.util.Random;

/**
 * The AlphaBeta implements the alpha beta algorithm and return
 * it using the static alphaBeta method for use in the AlphaBetaAI
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class AlphaBeta extends Algorithm{

    /**
     * This method uses an minimax algorithm with alpha beta pruning to determine the best
     * move in a game of connect four.
     * @param b The Board to be read
     * @param depth The depth with which the algorithm is testing up till
     * @param alpha The higher bound
     * @param beta The lower bound
     * @param playerNum The current player
     * @return int best move
     */
    public static int[] alphaBeta(Board b, int depth, int alpha, int beta, int playerNum){
        //if the depth is 0 or the game is over return the last move
        if (depth == 0 || b.isGameOver()){
            return b.getLastMove();
        }
        //If player 1, maximize board score
        if (playerNum == Board.PLAYER_1){
            int[] bestMove = new int[3];
            bestMove[2] = Integer.MIN_VALUE;
            //check nodes of next tree
            for (Board board: Board.getPossibleBoards(b, Board.PLAYER_2)) {
                //calls minimax using next player to determine minimum board score possible
                int[] move = alphaBeta(board, depth - 1, alpha, beta, Board.PLAYER_2);
                if (move[2] > bestMove[2] && !isLosingMove(b, move[1], Board.PLAYER_2))
                    bestMove = move;
                else if(move[2] == bestMove[2]){
                    if (Math.abs(move[1] - 3) < Math.abs(bestMove[1] - 3)){
                        bestMove = move;
                    }
                    else if (getRandom()){
                        bestMove = move;
                    }
                }
                //update upper bound
                if(bestMove[2] > alpha){
                    alpha = bestMove[2];
                }
                //if bounds cross, node and children can be ignored
                if(beta < alpha)
                    break;
            }
            return bestMove;
        }
        //if player 2, minimize board score
        else if(playerNum == 2){
            int[] bestMove = new int[3];
            bestMove[2] = Integer.MAX_VALUE;
            //check next nodes of tree
            for (Board board: Board.getPossibleBoards(b, Board.PLAYER_1)){
                //determine maximum loss using recursion
                int[] move = alphaBeta(board, depth - 1, alpha, beta, Board.PLAYER_1);
                if (move[2] < bestMove[2] && !isLosingMove(b, move[1], Board.PLAYER_2))
                    bestMove = move;
                else if (move[2] == bestMove[2]){
                    if (Math.abs(move[1] - 3) < Math.abs(bestMove[1] - 3)){
                        bestMove = move;
                    }
                    else if (getRandom()){
                        bestMove = move;
                    }
                }
                //update lower bound
                if(bestMove[2] < beta){
                    beta = bestMove[2];
                }
                //break if bounds intersect
                if(beta < alpha)
                    break;
            }
            //minimizes max loss
            return bestMove;
        }
        return null;
    }


}
