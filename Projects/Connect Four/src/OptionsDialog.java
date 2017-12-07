import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * The OptionsDialogue Class extends Dialog and contains a OptionsPane.
 * OptionsDialogue is created when the options menu is selected.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class OptionsDialog extends Dialog{
    private OptionsPane pane;

    public OptionsDialog(StartPane startPane){
        pane = new OptionsPane(startPane);
        getDialogPane().setContent(pane);
        ButtonType buttonTypeOk = new ButtonType("Start", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(buttonTypeOk);
        setHeight(300);
        setWidth(400);
        showAndWait();
        pane.setNewGame();
    }
}
