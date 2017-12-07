import javafx.scene.control.Button;

/**
 * The ChoiceButton implements Button and its main function is to set and retrieve the
 * column  number of the Button
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class ChoiceButton extends Button {

    private int value;

    public ChoiceButton(int num){
        value = num;
        setText("" + (num + 1));
    }

    public int getValue(){
        return value;
    }



}
