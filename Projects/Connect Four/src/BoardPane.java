import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.layout.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

/**
 * The BoardPane Class extends GridPane and serves as the GUI representation of the Board.
 * In this class, the more game logic is implemented such as the buttons for choosing moves
 * and adding players to the Board.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class BoardPane extends GridPane {
    private Board board;
    private int[][] tokens;
    private TokenPane[][] tokenPanes;
    private boolean play1move;
    private Player player1;
    private Player player2;
    private ChoiceButton[] choiceButtons;
    private Button oneStep;

    public BoardPane(Board board, Player p1, Player p2){
        player1 = p1;
        player2 = p2;
        play1move = true;
        this.board = board;
        tokens = board.getPieces();
        setTokenPanes();
        if(p1 instanceof HumanPlayer && p2 instanceof HumanPlayer){
            setPVP();
        }
        else if (p1 instanceof HumanPlayer && p2 instanceof AbstractAI)
        {
            setAIPlayer2();
        }
        else if (p1 instanceof AbstractAI && p2 instanceof HumanPlayer)
        {
            setAIPlayer1();
        }
        else{
            setAIvAI();
        }

    }

    public void setPVP(){
        choiceButtons = new ChoiceButton[Board.WIDTH];
        for (int i = 0; i < tokens[0].length; i++) {
            ChoiceButton button = new ChoiceButton(i);
            choiceButtons[i] = button;
            button.setPrefSize(100, 10);
            StackPane stackPane = new StackPane(button);
            button.setOnAction(e -> {
                int playerVal;
                if (play1move)
                    playerVal = player1.getValue();
                else playerVal = player2.getValue();
                play1move = !play1move;
                board.makeMove(button.getValue(), playerVal);
                if(!board.canMakeMove(button.getValue()))
                    button.setDisable(true);
                update();
                checkWin();
            });
            add(stackPane, i, tokens.length);
        }
        setSizing();
    }

    public void update(){
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[0].length; j++) {
                tokenPanes[i][j].changeColor(tokens[i][j]);
            }
        }
    }

    public void setAIPlayer2(){
        choiceButtons = new ChoiceButton[Board.WIDTH];
        for (int i = 0; i < tokens[0].length; i++) {
            ChoiceButton button = new ChoiceButton(i);
            choiceButtons[i] = button;
            button.setPrefSize(100, 10);
            StackPane stackPane = new StackPane(button);
            button.setOnAction(e -> {
                board.makeMove(button.getValue(), player1.getValue());
                if(!board.canMakeMove(button.getValue()))
                    button.setDisable(true);
                update();
                if(checkWin())
                    return;
                board.makeMove(player2.chooseMove(), player2.getValue());
                if(!board.canMakeMove(button.getValue()))
                    button.setDisable(true);
                update();
                checkWin();

            });
            add(stackPane, i, tokens.length);
        }
        setSizing();
    }

    public void setAIPlayer1(){
        board.makeMove(player1.chooseMove(), player1.getValue());
        update();
        choiceButtons = new ChoiceButton[Board.WIDTH];
        for (int i = 0; i < tokens[0].length; i++) {
            ChoiceButton button = new ChoiceButton(i);
            choiceButtons[i] = button;
            button.setPrefSize(100, 10);
            StackPane stackPane = new StackPane(button);
            button.setOnAction(e -> {
                board.makeMove(button.getValue(), player2.getValue());
                if(!board.canMakeMove(button.getValue()))
                    button.setDisable(true);
                update();
                if(checkWin())
                    return;
                board.makeMove(player1.chooseMove(), player1.getValue());
                if(!board.canMakeMove(button.getValue()))
                    button.setDisable(true);
                update();
                checkWin();

            });
            add(stackPane, i, tokens.length);
        }
        setSizing();
    }

    public void setAIvAI(){
        oneStep = new Button("One Step");
        oneStep.setPrefSize(110,20);
        add(oneStep, 3, 7);
        oneStep.setOnAction(e -> {
            if (play1move)
                board.makeMove(player1.chooseMove(), player1.getValue());
            else
                board.makeMove(player2.chooseMove(), player2.getValue());
            play1move = !play1move;
            update();
            checkWin();
        });

    }

    public void setSizing(){
        setPadding(new Insets(20, 20, 20, 20));
        for (TokenPane[] t:tokenPanes) {
            ColumnConstraints column = new ColumnConstraints();
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            getRowConstraints().add(row);
            column.setPercentWidth(100 / 7);
            getColumnConstraints().add(column);
        }
        //setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    public boolean checkWin(){
        if (board.isGameOver()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            alert.setContentText(board.getWinnerString());
            alert.showAndWait();
            disableButtons();
            return true;
        }
        return false;
    }

    public void startNewGame(){
        getChildren().clear();
        board = new Board();
        tokens = board.getPieces();
        setTokenPanes();
        if(player1 instanceof AbstractAI){
            ((AbstractAI) player1).setBoard(board);
        }
        if(player2 instanceof AbstractAI){
            ((AbstractAI) player2).setBoard(board);
        }
        if(player1 instanceof HumanPlayer && player2 instanceof HumanPlayer){
            setPVP();
        }
        else if (player1 instanceof HumanPlayer && player2 instanceof AbstractAI){
            setAIPlayer2();
        }
        else if (player1 instanceof AbstractAI && player2 instanceof HumanPlayer){
            setAIPlayer1();
        }
        else{
            setAIvAI();
        }
        play1move = true;
    }

    public void setTokenPanes(){
        tokenPanes = new TokenPane[tokens.length][tokens[0].length];
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[0].length; j++) {
                TokenPane t = new TokenPane();
                tokenPanes[i][j] = t;
                add(t, j, i);
            }
        }
    }

    public void disableButtons(){
        if(choiceButtons != null){
            for(ChoiceButton button: choiceButtons){
                if (button != null)
                    button.setDisable(true);
            }
        }
        if(oneStep != null){
            oneStep.setDisable(true);
        }
    }

}
