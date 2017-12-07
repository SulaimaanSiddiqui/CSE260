import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The TokenPane Class extends StackPane and is a GUI representation
 * of each token using a Circle and Rectangle
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public class TokenPane extends StackPane{
    private Circle c;
    private Rectangle r;

    public TokenPane(){
        c = new Circle(50);
        c.setFill(Color.WHITESMOKE);
        r = new Rectangle(100,100);
        r.setFill(Color.LIGHTYELLOW);
        this.getChildren().addAll(r ,c);
    }

    public void changeColor(int playerNum){
        if(playerNum == Board.PLAYER_1)
            c.setFill(Color.BLACK);
        else if(playerNum == Board.PLAYER_2)
            c.setFill(Color.RED);
        else
            c.setFill(Color.WHITESMOKE);
    }
}
