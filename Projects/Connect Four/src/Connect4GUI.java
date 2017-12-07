import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * The Connect4GUI is the main GUI which extends Application on which the every component is held together.
 * The main method starts the GUI.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class Connect4GUI extends Application {
    private Player player1;
    private Player player2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartPane startPane = new StartPane();
        OptionsMenu bar = new OptionsMenu(startPane);
        bar.prefWidthProperty().bind(primaryStage.widthProperty());
        BorderPane pane = new BorderPane(startPane);
        pane.setTop(bar);
        Scene scene = new Scene(pane, 750,685);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch();
    }
}
