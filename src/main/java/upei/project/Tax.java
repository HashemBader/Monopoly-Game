package upei.project;

/**
 * The Tax class represents a specific type of BoardSquare where players pay a tax amount.
 * It extends the BoardSquare abstract class and defines actions taken when a player lands on a Tax square.
 */
public class Tax extends BoardSquare {

    /**
     * Constructor to create a Tax instance.
     * @param iLoc Location index on the board.
     * @param name Name of the Tax square.
     */
    public Tax(int iLoc, String name) {
        super(iLoc, name);
    }

    /**
     * Method triggered when a player lands on the Tax square.
     * Deducts a specific tax amount from the player based on the Tax square's position on the board.
     * @param player Player object representing the player on the Tax square.
     */
    @Override
    public void playerOnLocation(Player player) {
        if (player.getPos() == 4){
            player.subtractMoney(200); // Deducts $200 as tax when landing on position 4
        } else if (player.getPos() == 38) {
            player.subtractMoney(100); // Deducts $100 as tax when landing on position 38
        }
    }
}
