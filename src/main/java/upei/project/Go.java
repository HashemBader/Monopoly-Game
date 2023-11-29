package upei.project;
/**
 * The Go class represents the "Go" square on the game board.
 * Landing on this square allows the player to collect money.
 * Extends the BoardSquare abstract class.
 */
public class Go extends BoardSquare{
    /**

     */
    public Go(int iLoc, String name) {
        super(iLoc, name);
    }
    /**
     * Method that specifies the action when a player lands on the "Go" square.
     * Adds money to the player's balance.
     * @param player The player object representing the player who landed on the square.
     */
    @Override
    public void playerOnLocation(Player player) {
        // Player receives $200 upon landing on "Go"
        player.addMoney(200);
    }
}
