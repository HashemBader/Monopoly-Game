package upei.project;

import java.util.Random;

/**
 * The Jail class represents a specific type of BoardSquare where a player can be sent to jail.
 * It defines actions taken when a player lands on the Jail square.
 * Extends the BoardSquare abstract class.
 * Implements Randomizable
 */
public class Jail extends BoardSquare implements Randomizable{

    Random random = new Random(); // Random object for dice rolling

    /**
     * Constructor to create a Jail instance.
     * @param iLoc Location index on the board.
     * @param name Name of the Jail square.
     */
    public Jail(int iLoc, String name) {
        super(iLoc, name);
    }

    /**
     * Method determines the action to be taken when a player lands on the Jail square.
     * Updates player's position to the Jail(Just visiting square), deducts money, and allows chances to roll dice for release.
     * @param player Player object representing the player on the Jail square.
     */
    @Override
    public void playerOnLocation(Player player) {
        player.setPos(10); // Player is moved to the Jail position(Just visiting square)
        player.subtractMoney(50); // Deducts money as a penalty for being in Jail temporarily
        final int MAX_TRIES = 3; // Maximum attempts to roll a double
        int tries = 0;
        int dice1, dice2;

        while (tries < MAX_TRIES) {
            dice1 = (int) (random.nextFloat()*6) + 1; // Rolls the first dice (value between 1 and 6)
            dice2 = (int) (random.nextFloat()*6) + 1; // Rolls the second dice (value between 1 and 6)

            if (dice1 == dice2) {
                player.addMoney(50); // If a double is rolled, player gets money back and leaves Jail
                break;
            }
            tries++;
        }
    }

    /**
     * Sets the seed for the random number generator. (for test-cases purposes)
     * @param seed The seed value to set for the random number generator.
     */
    public void setSeed(long seed){
        this.random.setSeed(seed);
    }
}
