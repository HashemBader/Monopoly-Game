package upei.project;

/**
 * The MiscSquare class represents a miscellaneous square on the game board.
 * It extends the BoardSquare abstract class and defines the behavior for this type of square.
 */
public class MiscSquare extends BoardSquare {

    /**
     * Constructor to create a MiscSquare instance.
     * @param iLoc Location index on the board.
     * @param name Name of the miscellaneous square.
     */
    public MiscSquare(int iLoc, String name) {
        super(iLoc, name);
    }

    /**
     * Method determines the action to be taken when a player lands on the miscellaneous square.
     * Does nothing as it is a miscellaneous square.
     * @param player Player object representing the player on the miscellaneous square.
     */
    @Override
    public void playerOnLocation(Player player) {
        // Do nothing as it is a miscellaneous square
    }
}
