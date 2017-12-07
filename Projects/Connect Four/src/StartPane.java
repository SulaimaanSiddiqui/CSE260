import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The StartPane Class extends StackPane and creates a start screen
 * and creates a BoardPane within based on the options selected
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class StartPane extends StackPane {
    private BoardPane gamePane;
    //private VBox buttonBox;
    private FlowPane buttonBox;
    private Label title;
    private BorderPane mainPane;
    private ImageView backGround;
    private ComboBox<String> player1;
    private ComboBox<String> player2;
    private ComboBox<Integer> play1Depth;
    private ComboBox<Integer> play2Depth;
    private FlowPane play1DepthPane;
    private FlowPane play2DepthPane;
    private boolean hasStarted;

    public StartPane(){
        mainPane = new BorderPane();
        mainPane.setPrefHeight(650);
        mainPane.setPrefWidth(750);
        buttonBox = new FlowPane(Orientation.VERTICAL);
        mainPane.setCenter(buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setVgap(35);
        buttonBox.setPadding(new Insets(0,0,0,50));
        addImage();
        addLabel();
        addStartButton();
        addMode();
        addDifficulties();
        hasStarted = false;
        getChildren().add(mainPane);
    }

    public void addStartButton(){
        Button start = new Button("Start");
        start.setPrefSize(210, 30);
        start.setAlignment(Pos.CENTER);
        start.setOnAction(e -> {
            buttonBox.getChildren().removeAll(start);
            mainPane.getChildren().removeAll(buttonBox);
            getChildren().removeAll(mainPane, backGround);
            addBoardPane();
        });
        FlowPane centerer = new FlowPane();
        centerer.getChildren().addAll(new Label("\t\t"), start);
        buttonBox.getChildren().add(centerer);
    }

    public void addMode(){
        player1 = Options.getOptions();
        FlowPane player1Pane = new FlowPane();
        player1Pane.getChildren().addAll(new Label("\t\tPlayer 1: "), player1);
        player1Pane.setHgap(20);
        buttonBox.getChildren().add(player1Pane);
        player1.setOnAction(e ->
                {
            if(player1.getSelectionModel().getSelectedItem().equals("Minimax AI")
                    || player1.getSelectionModel().getSelectedItem().equals("Alpha Beta AI"))
                play1DepthPane.setDisable(false);
            else
                play1DepthPane.setDisable(true);
            }

            );
        player2 = Options.getOptions();
        FlowPane player2Pane = new FlowPane();
        player2Pane.getChildren().addAll(new Label("\t\tPlayer 2: "), player2);
        player2Pane.setHgap(20);
        buttonBox.getChildren().add(player2Pane);
        player2.setOnAction(e ->
                {
                    if(player2.getSelectionModel().getSelectedItem().equals("Minimax AI")
                            || player2.getSelectionModel().getSelectedItem().equals("Alpha Beta AI"))
                        play2DepthPane.setDisable(false);
                    else
                        play2DepthPane.setDisable(true);
                }

        );
    }

    public void addDifficulties(){
        play1Depth = Options.getDepthBox();
        play1DepthPane = new FlowPane();
        play1DepthPane.setHgap(35);
        play1DepthPane.getChildren().addAll(new Label("\t\tPlayer 1 AI Depth: "), play1Depth);
        play1DepthPane.setDisable(true);
        play2Depth = Options.getDepthBox();
        play2DepthPane = new FlowPane();
        play2DepthPane.setHgap(35);
        play2DepthPane.getChildren().addAll(new Label("\t\tPlayer 2 AI Depth: "), play2Depth);
        play2DepthPane.setDisable(true);
        buttonBox.getChildren().addAll(play1DepthPane, play2DepthPane);
    }

    public void addBoardPane(){
        Board b = new Board();
        String p1String = player1.getSelectionModel().getSelectedItem();
        int p1Depth = 666;
        if (!play1Depth.isDisabled()){
            p1Depth = play1Depth.getSelectionModel().getSelectedItem();
        }
        Player p1 = PlayerFactory.getPlayer(p1String, Board.PLAYER_1,
                b, p1Depth);
        String p2String = player2.getSelectionModel().getSelectedItem();
        int p2Depth = 666;
        if (!play2Depth.isDisabled()){
            p2Depth = play2Depth.getSelectionModel().getSelectedItem();
        }
        Player p2 = PlayerFactory.getPlayer(p2String, Board.PLAYER_2,
                b, p2Depth);
        hasStarted = true;
        gamePane = new BoardPane(b, p1, p2);
        getChildren().add(gamePane);
    }


    public void setGamePane(BoardPane boardPane){
        hasStarted = true;
        getChildren().remove(gamePane);
        gamePane = boardPane;
        getChildren().add(gamePane);
    }

    public void addImage(){
        Image image = new Image("background.jpg");
        backGround = new ImageView(image);
        backGround.setFitWidth(750);
        backGround.setFitHeight(700);
        backGround.setPreserveRatio(true);
        backGround.setSmooth(true);
        backGround.setCache(true);
        backGround.setOpacity(0.5);
        getChildren().add(backGround);
    }

    public void addLabel(){
        title = new Label("Connect 4");
        title.setFont(Font.font("Verdana", 60));
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.BLUE);
        FlowPane centerer = new FlowPane(title);
        mainPane.setOpaqueInsets(new Insets(35,0,0,0));
        centerer.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(title);
    }


    public void resetGame(){
        gamePane.startNewGame();
    }

    public boolean hasStarted(){
        return hasStarted;
    }
}
