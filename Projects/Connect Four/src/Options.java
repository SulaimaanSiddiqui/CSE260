import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.Arrays;
import java.util.List;

/**
 * Options is a class that contains static methods to return all the Combo Boxes
 * that contain the options for the Connect Four game GUI
 */
public class Options {
    public static final String[] choice = {"Human", "Minimax AI", "Simple AI", "Alpha Beta AI"};
    public static final List<String> choices = Arrays.asList(choice);
    public static ComboBox<String> options;
    public static ComboBox<Integer> depth;

    public Options(){
        for (int i = 0; i < choices.size(); i++) {
            options.getItems().add(choices.get(i));
        }
    }

    public static ComboBox<String> getOptions(){
        options = new ComboBox<>();
        for (int i = 0; i < choices.size(); i++) {
            options.getItems().add(choices.get(i));
        }
        options.setValue(choice[0]);
        return options;
    }

    public static ComboBox<Integer> getDepthBox(){
        depth = new ComboBox<>();
        for (int i = 0; i < 8; i++) {
            depth.getItems().add(i+1);
        }
        depth.setValue(4);
        return depth;
    }

}
