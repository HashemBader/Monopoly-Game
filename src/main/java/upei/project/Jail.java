package upei.project;

import java.util.Random;

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
    Random random = new Random();
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
        player.subtractMoney(50); // Deducts money as a penalty for being in Jail temporarily
        final int MAX_TRIES = 3;// Maximum attempts to roll a double
        int tries = 0;
        int dice1, dice2;
        while (tries < MAX_TRIES) { // 6/36 * 6/36 = 1/36
            dice1 = (int) (random.nextFloat()*6) + 1;// Rolls the first dice
            dice2 = (int) (random.nextFloat()*6) + 1;// Rolls the second dice
            if (dice1 == dice2) {
                player.addMoney(50); // If a double is rolled, player gets money back and leaves Jail
                break;
            }
            tries++;
        }
    }

    public void setSeed(long seed){
        this.random.setSeed(seed);
    }
}
