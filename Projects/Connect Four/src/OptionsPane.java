import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * The OptionsPane Class contains options for the Connect4 Game. OptionsPane is used in an
 * OptionsDialog, which is created when the OptionsMenu is selected.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class OptionsPane extends StackPane {
    private StartPane gamePane;
    private ComboBox<String> player1;
    private ComboBox<String> player2;
    private ComboBox<Integer> player1depth;
    private ComboBox<Integer> player2depth;
    private FlowPane p1DepthPane;
    private FlowPane p2DepthPane;
    private FlowPane mainPane;

    public OptionsPane(StartPane startPane){
        gamePane = startPane;
        mainPane = new FlowPane(Orientation.VERTICAL);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(15);
        addPlayers();
        addDifficulties();
        getChildren().add(mainPane);
    }

    public void addPlayers(){
        player1 = Options.getOptions();
        FlowPane p1Pane = new FlowPane();
        p1Pane.setHgap(15);
        p1Pane.getChildren().addAll(new Label("Player 1: "), player1);
        player2 = Options.getOptions();
        FlowPane p2Pane = new FlowPane();
        p2Pane.setHgap(15);
        p2Pane.getChildren().addAll(new Label("Player 2: "), player2);
        mainPane.getChildren().addAll(p1Pane, p2Pane);
        player1.setOnAction(e ->
                {
                    if(player1.getSelectionModel().getSelectedItem().equals("Minimax AI")
                            || player1.getSelectionModel().getSelectedItem().equals("Alpha Beta AI"))
                        p1DepthPane.setDisable(false);
                    else
                        p1DepthPane.setDisable(true);
                }

        );
        player2.setOnAction(e ->
                {
                    if(player2.getSelectionModel().getSelectedItem().equals("Minimax AI")
                            || player2.getSelectionModel().getSelectedItem().equals("Alpha Beta AI"))
                        p2DepthPane.setDisable(false);
                    else
                        p2DepthPane.setDisable(true);
                }

        );
    }

    public void addDifficulties(){
        player1depth = Options.getDepthBox();
        p1DepthPane = new FlowPane();
        p1DepthPane.getChildren().addAll(new Label("Player 1 AI Depth: "), player1depth);
        player2depth = Options.getDepthBox();
        p2DepthPane = new FlowPane();
        p2DepthPane.getChildren().addAll(new Label("Player 2 AI Depth: "), player2depth);
        p1DepthPane.setDisable(true);
        p2DepthPane.setDisable(true);
        mainPane.getChildren().addAll(p1DepthPane, p2DepthPane);
    }

    public void setNewGame(){
        Board b = new Board();
        String p1String = player1.getSelectionModel().getSelectedItem();
        int p1Depth = 666;
        if (!player1depth.isDisabled()){
            p1Depth = player1depth.getSelectionModel().getSelectedItem();
        }
        Player p1 = PlayerFactory.getPlayer(p1String, Board.PLAYER_1,
                b, p1Depth);
        String p2String = player2.getSelectionModel().getSelectedItem();
        int p2Depth = 666;
        if (!player2depth.isDisabled()){
            p2Depth = player2depth.getSelectionModel().getSelectedItem();
        }
        Player p2 = PlayerFactory.getPlayer(p2String, Board.PLAYER_2,
                b, p2Depth);
        gamePane.setGamePane(new BoardPane(b, p1, p2));
    }

}
