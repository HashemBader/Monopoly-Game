package upei.project;

import java.util.Arrays;
/**
 * The Utility class represents utility properties in the game.
 * It extends the Property class and defines specific behavior for utility properties.
 */
public class Utility extends Property {
    private Player owner;// The player who owns the utility
    private int buyPrice;// The purchase price of the utility
    /**
     * Constructor to create a Utility instance with a specific location, name, and buy price.
     * @param iLoc Location index of the utility on the board.
     * @param name Name of the utility.
     * @param buyPrice Price at which the utility can be purchased.
     */
    public Utility(int iLoc, String name, int buyPrice){
        super(iLoc, name);// Calls the constructor of the parent class Property
        this.buyPrice = buyPrice;
        this.owner = null;

    }
    /**
     * Defines the action when a player lands on a Utility square.
     * @param player The player object representing the player who landed on the utility.
     */
    @Override
    public void playerOnLocation(Player player) {
        if (getOwner() == player){
            return; // If the player is already the owner, do nothing
        }
        else if (getOwner() != player && getOwner() == null){// If the utility has no owner
            if (player.makeChoice(this.iLoc)){ // the player will make a choice to buy it or not
                setOwner(player);
                player.lessMoney(getBuyPrice());
            }
        }
        else{// If another player owns the utility, pay rent to the owner
            player.lessMoney(this.getRent(Driver.getDiceVal()));
            getOwner().addMoney(this.getRent(Driver.getDiceVal()));
        }
    }
    // Getter and setter methods
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * Returns the rent to be paid for landing on this utility.
     * @return The rent amount calculated based on the dice value.
     */
    public int getRent(int diceVal){
        return calcRent(diceVal);
    }

    /**
     * Calculates the rent amount for this utility based on some logic (TODO: Specify the logic).
     * @return The calculated rent amount.
     */

    private int calcRent(int diceVal){
        return diceVal*3; // might delete later
    }

}
