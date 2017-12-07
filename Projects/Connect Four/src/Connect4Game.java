public class Connect4Game {

    private Board board;
    private Player p1;
    private Player p2;

    public Connect4Game(Board b, Player player1, Player player2){
        board = b;
        p1 = player1;
        p2 = player2;
        start();
    }

    public void start(){
        while (!board.isGameOver()){
            System.out.println(board);
            board.makeMove(p1.chooseMove(), p1.getValue());
            if (board.isGameOver()){
                System.out.println("Player 1 wins");
                break;
            }
            System.out.println(board);
            board.makeMove(p2.chooseMove(), p2.getValue());
            if (board.isGameOver()){
                System.out.println("Player 2 wins");
                break;
            }
        }
        System.out.println(board);
    }

    public static void main(String[] args) {
        Board board = new Board();
        HumanPlayer p1 = new HumanPlayer(1);
        //HumanPlayer p2 = new HumanPlayer(2);
        SimpleAI p2 = new SimpleAI(board, 2);
        new Connect4Game(board, p1, p2);
    }
}
