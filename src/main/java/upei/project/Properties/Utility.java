package upei.project.Properties;

import upei.project.MonopolyGame;
import upei.project.Player;

/**
 * The Utility class represents a utility property on the game board.
 * It extends the Property class and implements specific behaviors for utility properties.
 */
public class Utility extends Property {

    /**
     * Constructor to create a Utility instance with specified parameters.
     * @param iLoc Location index of the utility property on the board.
     * @param name Name of the utility property.
     * @param buyPrice Buying price of the utility property.
     */
    public Utility(int iLoc, String name, int buyPrice){
        super(iLoc, name, buyPrice);
        this.owner = null;
    }

    /**
     * Determines the action to be taken when a player lands on a utility property.
     * @param player The player object representing the player who landed on the property.
     */
    @Override
    public void playerOnLocation(Player player) {
        if (getOwner() == player){// the player is the owner, do nothing
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){// Property not owned
            if (player.makeChoice(this)){// make a choice depend on the strategy
                this.setOwner(player);// buys the property
                player.subtractMoney(this.getBuyPrice());// deduct the player's money by the price of the property
            }
        }
        else{ // A player has landed on another player's property
            player.subtractMoney(this.calcRent());// Deduct rent from the current player
            this.getOwner().addMoney(this.calcRent());// Add rent to the property owner's money
        }
    }

    /**
     * Calculates the rent amount for the utility property based on the dice value and number of owned utilities.
     * @return The calculated rent amount.
     */
    public int calcRent(){
        if(this.getOwner().getLandsOwnedOfType(Utility.class).size() == 1){// has 1 utility
            return MonopolyGame.getDiceVal() * 4;
        } else {// has 2 utilities
            return MonopolyGame.getDiceVal() * 10;
        }
    }

    /**
     * Overrides the toString() method to provide a string representation of the Utility object.
     * @return A string representation of the Utility object.
     */
    @Override
    public String toString() {
        return "Utility{" +
                super.toString()
                + "}";
    }
}
