/**
 * The Players Interface is the interface all concrete Player types must implement. The BoardPane Class
 * requires two Player types as parameters.
 *
 * @author  Sulaimaan Siddiqui
 * @version 1.0
 * @since 12/6/17
 */
public interface Player {

    int chooseMove();
    int getValue();

}
