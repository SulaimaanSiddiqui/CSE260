import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class OptionsMenu extends MenuBar {
    private StartPane startPane;
    private Menu file;

    public OptionsMenu(StartPane startPane){
        this.startPane = startPane;
        file = new Menu("File");
        getMenus().add(file);
        addNewGameMenu();
        addOptionsMenu();
        addQuitMenu();
    }

    public void addNewGameMenu(){
        MenuItem newGameMenu = new MenuItem("New Game");
        newGameMenu.setOnAction(e -> {
            if (!startPane.hasStarted()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Start game first");
                Optional<ButtonType> result = alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("New Game");
                alert.setHeaderText("New Game");
                alert.setContentText("Do you wish to start a new game?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    startPane.resetGame();
                }
            }
        });
        file.getItems().addAll(newGameMenu);
    }


    public void addQuitMenu(){
        MenuItem quitMenu = new MenuItem("Quit");
        quitMenu.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit Game");
            alert.setHeaderText("Quit Game");
            alert.setContentText("Do you wish to quit?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
            }
        });
        file.getItems().addAll(quitMenu);
    }

    public void addOptionsMenu(){
        MenuItem optionsMenu = new MenuItem("Options");
        optionsMenu.setOnAction(e -> {
            if (!startPane.hasStarted()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Start game first");
                alert.showAndWait();
            }
            else {
                new OptionsDialog(startPane);
            }
        });
        file.getItems().addAll(optionsMenu);
    }
}
