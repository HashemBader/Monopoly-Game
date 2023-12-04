package upei.project;

import java.util.Random;

/**
 * The WildSquare class represents the Community Chest and Chance cards type of BoardSquare
 * triggering random events when a player lands on it.
 * It extends the BoardSquare abstract class.
 * Implements Randomizable
 */
public class WildSquare extends BoardSquare implements Randomizable{
    Random random = new Random();
    /**
     * Constructor to create a WildSquare instance with a specific location and name.
     *
     * @param iLoc Location on the board.
     * @param name Name of the WildSquare.
     */
    public WildSquare(int iLoc, String name) {
        super(iLoc, name);
    }

    /**
     * Defines the action that occurs when a player lands on the WildSquare.
     * It triggers various random events affecting the player's position or money.
     *
     * @param player The player object representing the player who landed on the square.
     */
    @Override
    public void playerOnLocation(Player player) {
        int rand =(int) (random.nextFloat()*18) + 1;
        switch (rand){
            case 1 -> player.setPos(0);// advance to go
            case 2 -> player.addMoney(100);//Bank error in your favor. Collect $100.
            case 3 -> player.subtractMoney(50);//Doctor's fees. {fee} Pay $50
            case 4 -> player.addMoney(20);//From sale of stock you get $20
            case 5 -> player.subtractMoney(50);//School fees. Pay $50
            case 6 -> player.setPos(30);//Go to Jail.
            case 7 -> player.setPos(39);//Advance to EGYPT.
            case 8 -> player.setPos(8);//Advance to MOROCCO.
            case 9 -> player.moveN(4);//Go Forward {4} Spaces
            case 10 -> player.setPos(15);// advance to PORTSAID STATION
            case 11 -> player.setPos(28);// advance to water company
            case 12 -> player.setPos(29);// advance to EMIRATES
            case 13 -> player.subtractMoney(15);// speeding fine $15
            case 14 -> player.subtractMoney(100);// pay hospital fees $100
            case 15 -> player.addMoney(10);// you have won second prize in a beauty contest. Collect $10
            case 16 -> player.setPos(25);// advance to T3 STATION
            case 17 -> player.setPos(37);// advance to QATAR
        }
    }
    /**
     * Sets the seed for the random number generator used in this class(for test-cases).
     *
     * @param seed The seed value to set for the random number generator.
     */
    public void setSeed(long seed) {
        this.random.setSeed(seed);
    }

}
