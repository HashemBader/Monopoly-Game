package upei.project;
/**
 * The Taxe class represents a type of BoardSquare that imposes taxes on players
 * when they land on specific locations on the game board.
 * It extends the BoardSquare class.
 */
public class Taxe extends BoardSquare{

    /**
     * Constructor to create a Taxe instance with a specified location and name.
     *
     * @param iLoc Location on the board where the tax square is located.
     * @param name Name of the tax square.
     */
    public Taxe(int iLoc, String name) {
        super(iLoc, name);// Calls the constructor of the parent class (BoardSquare)
    }
    /**
     * Implements the action to be taken when a player lands on this Taxe square.
     * If the player lands on specific positions, it deducts a certain amount of money from the player.
     *
     * @param player The player object representing the player who landed on the tax square.
     */
    @Override
    public void playerOnLocation(Player player) {
        if (player.getPos() == 4){
            player.lessMoney(200);// Deducts 200 money from the player when he lands on position 4
        } else if (player.getPos() == 38) {
            player.lessMoney(100);// Deducts 100 money from the player when he lands on position 38
        }
    }
}
