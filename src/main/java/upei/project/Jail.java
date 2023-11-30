package upei.project;
/**
 * The Jail class represents a specific type of BoardSquare where a player can be sent to jail.
 * It defines actions taken when a player lands on the Jail square.
 * Extends the BoardSquare abstract class.
 */
public class Jail extends BoardSquare{
    /**
     * Constructor to create a Jail instance.
     * @param iLoc Location index on the board.
     * @param name Name of the Jail square.
     */
    public Jail(int iLoc, String name) {
        super(iLoc, name);
    }
    /**
     * Method triggered when a player lands on the Jail square.
     * Updates player's position to the Jail, deducts money, and allows chances to roll dice for release.
     * @param player Player object representing the player on the Jail square.
     */
    @Override
    public void playerOnLocation(Player player) {
        player.setPos(10); // Player is moved to the Jail position
        player.lessMoney(50); // Deducts money as a penalty for being in Jail temporarily
        final int MAX_TRIES = 3;// Maximum attempts to roll a double
        int tries = 0;
        int dice1;
        int dice2;
        while (tries < MAX_TRIES) {
            dice1 = Dice.rollDice1();// Rolls the first dice
            dice2 = Dice.rollDice1();// Rolls the second dice
            if (dice1 == dice2) {
                player.addMoney(50); // If a double is rolled, player gets money back and leaves Jail
                break;
            }
            tries++;
        }
    }
}
