/**
 * The AITesting class has a main method to test whether the AI algorithms
 * are working properly. The console prints the result of each game.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class AITesting {

    /**
     * This method sets up the games used to test whether the AI algorithms work properly.
     * Two AI players are created and games are created until the winner is determined and
     * printed.
     * @param args Not used
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                Board b = new Board();
                //AlphaBetaAI p1 = new AlphaBetaAI(b,1, i);
                AlphaBetaAI p2 = new AlphaBetaAI(b,2, j);
                MinimaxAI p1 = new MinimaxAI(b,1, i);
                //MinimaxAI p2 = new MinimaxAI(b,2, j);
                while (!b.isGameOver()){
                    b.makeMove(p1.chooseMove(), p1.getValue());
                    if (b.isGameOver()){
                        continue;
                    }
                    b.makeMove(p2.chooseMove(), p2.getValue());
                }
                System.out.println(result(b.getWinner(), i, j));
            }
        }
    }

    /**
     * This method prints the winner of a completed game and the depth of the two AIs.
     * @param winner Which Player won the game.
     * @param AI1Depth Depth of first AI.
     * @param AI2Depth Depth of second AI.
     * @return String A string formatted showing the winner and depth of AI players.
     */
    public static String result(int winner, int AI1Depth, int AI2Depth){
        if (winner == Board.PLAYER_1){
            return "Player " + winner + " AI of depth " + AI1Depth + " has won over AI of depth " + AI2Depth;
        }
        else if (winner == Board.PLAYER_2){
            return "Player " + winner + " AI of depth " + AI2Depth + " has won over AI of depth " + AI1Depth;
        }
        else {
            return "Tie game between AI of depth " + AI1Depth + " and depth " + AI2Depth;
        }
    }
}
