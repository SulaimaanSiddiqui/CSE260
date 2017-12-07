import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Board Class contains the main game board as well as the logic to make moves
 * as well as checking if the game has been won and calculating the score
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class Board {
    private int[][] pieces;
    private static final int EMPTY = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    private int winner = 0;
    private int[] lastMove;
    private int lastScore;
    public static final int WIDTH = 7;
    public static final int HEIGHT = 6;

    /**
     * Default constructor for objects of Board. Creates an empty board.
     */
    public Board(){
        pieces = new int[HEIGHT][WIDTH];
        lastMove = new int[3];
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++)
                pieces[i][j] = EMPTY;
        }
    }

    /**
     * Overloaded constructor of Board. Creates a board using a 2D array of existing tokens.
     * Useful in copying objects of board without making a clone.
     * @param oldTokens The tokens of the board to be copied
     */
    public Board(int[][] oldTokens){
        lastMove = new int[3];
        pieces = new int[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++)
                pieces[i][j] = oldTokens[i][j];
        }
    }

    /**
     * This method checks if a column if full.
     * @param col The column to search.
     * @return boolean Whether the column is full.
     */
    public boolean canMakeMove(int col){
        for (int i = HEIGHT - 1; i >= 0; i--){
            if (pieces[i][col] == EMPTY)
                return true;
        }
        return false;
    }

    /**
     * This method checks for a full board.
     * @return boolean. Whether every column is full
     */
    public boolean noMovesPossible(){
        for (int i = 0; i < WIDTH; i++){
            if (canMakeMove(i))
                return false;
        }
        return true;
    }

    /**
     * This method makes a move for a given player.
     * The method also keeps track of the last move.
     * The Game Score is recorded as the last element of the last move array. This is for the algorithms.
     * @param col The selected column
     * @param player The selected player
     */
    public void makeMove(int col, int player){
        if (!canMakeMove(col)){
            return;
        }
        int row = getLowestEmpty(col);
        pieces[row][col] = player;
        lastMove[0] = row;
        lastMove[1] = col;
        lastMove[2] = getScore();
    }

    /**
     * This method makes a move but does not keep track of the move made.
     * The reason why is to save memory since the use is to calculate Game Score and not used by algorithms.
     * @param col The selected column
     * @param player The selected player
     */
    public void makeNonMove(int col, int player){
        if (!canMakeMove(col)){
            return;
        }
        int row = getLowestEmpty(col);
        pieces[row][col] = player;
    }

    /**
     * This method returns the last move and game score
     * @return int[] Row, Col, and Score of the last move.
     */
    public int[] getLastMove(){
        return lastMove;
    }

    /**
     * This method checks in all directions if a Connect Four has been made.
     * @return boolean Whether a player has made a Connect Four
     */
    public boolean isGameOver(){
        if (noMovesPossible())
            return true;
        else {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (checkVertical(i,j) || checkHorizontal(i,j) ||
                            checkDiagonalDown(i,j) || checkDiagonalUp(i,j)){
                        winner = pieces[i][j];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns the lowest empty row in a column.
     * @param col The selected column
     * @return int The lowest row number
     */
    public int getLowestEmpty(int col){
        for (int i = HEIGHT - 1; i >= 0; i--){
            if (pieces[i][col] == EMPTY)
                return i;
        }
        return -1;
    }

    /**
     * This method returns a string representation of the Connect Four board.
     * Not used, but useful for debugging.
     * @return String Representation of Board
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < pieces.length; i++){
            for (int j = 0; j < pieces[0].length; j++) {
                s += " " + pieces[i][j];
            }
            s += "\n";
        }
        return s;
    }

    /**
     * This method checks horizantally forward to catch a connect 4
     * @param r The selected row position
     * @param c The selected column
     * @return whether there is a connect 4
     */
    public boolean checkHorizontal(int r, int c){
        int currValue = pieces[r][c];
        if (currValue == EMPTY)
            return false;
        else if ((c + 3) >= pieces[0].length)
            return false;
        else{
            for (int i = 1; i < 4; i++) {
                if (currValue != pieces[r][c+i])
                    return false;
            }
        }
        return true;
    }

    /**
     * This method checks vertically down to catch a connect 4
     * @param r The selected row position
     * @param c The selected column
     * @return whether there is a connect 4
     */
    public boolean checkVertical(int r, int c){
        int currValue = pieces[r][c];
        if (currValue == EMPTY)
            return false;
        else if ((r + 3) >= pieces.length)
            return false;
        else{
            for (int i = 1; i < 4; i++) {
                if (currValue != pieces[r+i][c])
                    return false;
            }
        }
        return true;
    }

    /**
     * This method checks diagonally down to catch a connect 4
     * @param r The selected row position
     * @param c The selected column
     * @return whether there is a connect 4
     */
    public boolean checkDiagonalDown(int r, int c){
        int currValue = pieces[r][c];
        if (currValue == EMPTY)
            return false;
        else if ( ((r + 3) >= pieces.length) || (c + 3) >= pieces[0].length)
            return false;
        else{
            for (int i = 1; i < 4; i++) {
                if (currValue != pieces[r+i][c+i])
                    return false;
            }
        }
        return true;
    }

    /**
     * This method checks diagonally up to catch a connect 4
     * @param r The selected row position
     * @param c The selected column
     * @return whether there is a connect 4
     */
    public boolean checkDiagonalUp(int r, int c){
        int currValue = pieces[r][c];
        if (currValue == EMPTY)
            return false;
        else if ( ((r - 3) < 0) || (c + 3) >= pieces[0].length)
            return false;
        else{
            for (int i = 1; i < 4; i++) {
                if (currValue != pieces[r-i][c+i])
                    return false;
            }
        }
        return true;
    }


    /**
     * This method returns the 2D array of pieces
     * @return int[][] Board pieces
     */
    public int[][] getPieces(){
        return pieces;
    }

    /**
     * This method returns the winner of the game. If winner is 0, there is no winner
     * @return int winner
     */
    public int getWinner(){
        return winner;
    }

    /**
     * This method turns the winner into a string representation
     * @return String winner representation
     */
    public String getWinnerString(){
        if(winner == PLAYER_1){
            return "Player 1 Wins";
        }
        else if (winner == PLAYER_2){
            return "Player 2 Wins";
        }
        else return "Tie game";
    }

    /**
     * This method scores the board according to a premade heuristic. This
     * portion has been the most difficult part of the project becuase of how volatile it is.
     * I ended up writing this portion around three times. Each heuristic is imperfect,
     * but I decided with the current one after some tinkering. I left the old methods commented out
     *
     * @return int The score of the game. Positive means player 1 is winning. Negative means player 2 is.
     */
    public int getScore(){

        //This is the fist method. Was supposed to score based on longest chain, but imperfect
        /*
        int play1score = 0;
        int play2score = 0;
        //play1score = longestChain(PLAYER_1) * 10;
        //play2score = longestChain(PLAYER_2) * 10;
        if (isGameOver()){
            if(getWinner() == Board.PLAYER_1){
                play1score += 10000;
            }
            else if(getWinner() == Board.PLAYER_2){
                play2score += 10000;
            }
        }

        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                if(pieces[i][j] == PLAYER_1){
                    play1score -= Math.abs(3 - j) * 2;
                    play2score += Math.abs(3 - j) * 2;
                }
                else if(pieces[i][j] == PLAYER_2) {
                    play2score -= Math.abs(3 - j) * 2;
                    play1score += Math.abs(3 - j) * 2;
                }
            }
        }

        lastScore = play1score - play2score;
        return play1score - play2score;

        //The second method. Scored based on number of chains of three and two
        //int p1score = 0;
        //int p2score = 0;
        if (isGameOver()){
            if(getWinner() == Board.PLAYER_1){
                p1score += 1000;
            }
            else if(getWinner() == Board.PLAYER_2){
                p2score += 1000;
            }
        }
        //p1score += checkNumInARow(3, PLAYER_1) * 9 + checkNumInARow(2, PLAYER_1) * 4
          //      + checkNumInARow(1, PLAYER_1);
        //p2score += checkNumInARow(3, PLAYER_2) * 9 + checkNumInARow(2, PLAYER_2) * 4
          //      + checkNumInARow(1, PLAYER_2);
            }
        }
        return p1score - p2score;*/

        //If Player has one, the score swings high in their direction
        int play1score = 0;
        int play2score = 0;
        if (isGameOver()){
            if(getWinner() == Board.PLAYER_1){
                play1score += 10000;
            }
            else if(getWinner() == Board.PLAYER_2){
                play2score += 10000;
            }
        }

        //Add some weight to center positions
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                if(pieces[i][j] == PLAYER_1){
                    //play1score += Math.abs(3 - j) * 1;
                    play2score -= Math.abs(3 - j) * 0.5;
                }
                else if(pieces[i][j] == PLAYER_2) {
                    //play2score += Math.abs(3 - j) * 1;
                    play1score -= Math.abs(3 - j) * 0.5;
                }
            }
        }
        //Score players based on how quickly they can win and how many in a row they have
        play1score += num1wins(PLAYER_1) * 10   + num2wins(PLAYER_1) * 4 + checkNumInARow(2, PLAYER_1) * 2;
        play2score += num1wins(PLAYER_2) * 10   + num2wins(PLAYER_2) * 4 + checkNumInARow(2, PLAYER_2) * 2;

        lastScore = play1score - play2score;
        return play1score - play2score;
    }

    /**
     * This method calculates the longest one directional chain of a player. Method is unused
     * @param playerNum The player
     * @return The longest chain
     */
    public int longestChain(int playerNum){
        int longestChain = 0;
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                if(pieces[i][j] == playerNum){
                    int chain = maxChain(i, j);
                    if (chain > longestChain){
                        longestChain = chain;
                    }
                }
            }
        }
        return longestChain;
    }

    /**
     * This method calculates the max chain for an individual piece on the board in four direction.
     * Method is unused after changing scoring method
     * @param row Row
     * @param col Column
     * @return
     */
    public int maxChain(int row, int col){
        List<Integer> choices= new ArrayList<>();
        //choices.add(1);
        int i = 0;
        int count = 1;
        while(row + i < HEIGHT){
            if(pieces[row + i][col] == pieces[row][col]){
                count++;
            }
            else break;
            i++;
        }
        choices.add(count);
        count = 0;
        i = 0;
        while (col + i < WIDTH){
            if(pieces[row][col + i] == pieces[row][col]){
                count++;
            }
            else break;
            i++;
        }
        choices.add(count);
        count = 0;
        i = 0;
        while (row + i < HEIGHT && col + i < WIDTH){
            if(pieces[row + i][col + i] == pieces[row][col]){
                count++;
            }
            else break;
            i++;
        }
        choices.add(count);
        count = 0;
        i = 0;
        while (row - i >= 0 && col + i < WIDTH){
            if(pieces[row - i][col + i] == pieces[row][col]){
                count++;
                i++;
            }
            i++;
        }
        choices.add(count);
        return Collections.max(choices);
    }


    /**
     * This method checks the amount of times a player has a selected of number of pieces in a row.
     * @param num Number of times
     * @param player selected player
     * @return
     */
    public int checkNumInARow(int num, int player){
        int times = 0;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                times += checkVertical(num, i, j, player) + checkHorizontal(num, i, j, player) +
                        checkDiagonalDown(num, i, j, player) + checkDiagonalUp(num, i, j, player) +
                        checkDiagonalDownBack(num, i, j, player) + checkDiagonalUpBack(num, i, j, player)
                        + checkHorizontalBack(num, i, j, player);
                ;
            }
        }
        return times;
    }

    public int checkHorizontal(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ((c + numTimes) > pieces[0].length)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r][c+i])
                    return 0;
            }
        }
        return 1;
    }

    public int checkHorizontalBack(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ((c - numTimes) < 0)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r][c-i])
                    return 0;
            }
        }
        return 1;
    }

    public int checkVertical(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ((r - numTimes) < 0)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r-i][c])
                    return 0;
            }
        }
        return 1;
    }

    public int checkDiagonalDown(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ( ((r + numTimes) > pieces.length) || (c + numTimes) > pieces[0].length)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r+i][c+i])
                    return 0;
            }
        }
        return 1;
    }

    public int checkDiagonalUp(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ( ((r - numTimes) < 0) || (c + numTimes) >= pieces[0].length)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r-i][c+i])
                    return 0;
            }
        }
        return 1;
    }

    public int checkDiagonalDownBack(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ( ((r - numTimes) < 0) || (c - numTimes) < 0)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r-i][c-i])
                    return 0;
            }
        }
        return 1;
    }

    public int checkDiagonalUpBack(int numTimes, int r, int c, int player){
        int currValue = pieces[r][c];
        if (currValue != player)
            return 0;
        else if ( ((r + numTimes) >= HEIGHT) || (c - numTimes) < 0)
            return 0;
        else{
            for (int i = 1; i < numTimes; i++) {
                if (currValue != pieces[r+i][c-i])
                    return 0;
            }
        }
        return 1;
    }

    public static ArrayList<Board> getPossibleBoards(Board b, int player){
        ArrayList<Board> possibleBoards = new ArrayList<>();
        for (int i = 0; i < Board.WIDTH; i++) {
            if (b.canMakeMove(i)){
                //Board possibleBoard = (Board)(b.clone());
                Board possibleBoard = new Board(b.getPieces());
                possibleBoard.makeMove(i, player);
                possibleBoards.add(possibleBoard);
            }
        }
        return possibleBoards;
    }


    public int num2wins(int player){
        int count = 0;
        int i = 0;
        ArrayList<Board> possibleBoards = getPossibleBoards( player);
        ArrayList<Board> level2 = new ArrayList<>();
        for (Board b:possibleBoards) {
            if (!isGameOver())
                level2.addAll(getPossibleBoards(player));
            else if (winner == player)
                count++;
        }
        for (Board b: level2) {
            if (winner == player)
                count++;
        }
        return count;
    }

    public int num1wins(int player){
        int count = 0;
        int i = 0;
        ArrayList<Board> possibleBoards = getPossibleBoards(player);
        for (Board b: possibleBoards) {
            if (winner == player)
                count++;
        }
        return count;
    }

    private ArrayList<Board> getPossibleBoards(int player){
        ArrayList<Board> possibleBoards = new ArrayList<>();
        for (int i = 0; i < Board.WIDTH; i++) {
            if (canMakeMove(i)){
                //Board possibleBoard = (Board)(clone());
                Board possibleBoard = new Board(getPieces());
                possibleBoard.makeNonMove(i, player);
                //System.out.println("added 1");
                possibleBoards.add(possibleBoard);
            }
        }
        return possibleBoards;
    }
}
