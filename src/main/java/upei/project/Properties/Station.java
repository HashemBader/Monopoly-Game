package upei.project.Properties;

import upei.project.Player;

/**
 * The Station class represents a station property on the game board.
 * It extends the Property class and implements specific behaviors for station properties.
 */
public class Station extends Property {

    /**
     * Constructor to create a Station instance with specified parameters.
     * @param iLoc Location index of the station property on the board.
     * @param name Name of the station property.
     * @param buyPrice Buying price of the station property.
     */
    public Station(int iLoc, String name, int buyPrice) {
        super(iLoc, name, buyPrice, 25);
    }

    /**
     * Determines the action to be taken when a player lands on a station property.
     * @param player The player object representing the player who landed on the property.
     */
    @Override
    public void playerOnLocation(Player player) {
        if (this.getOwner() == player){// the player is the owner, do nothing
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){ // Property not owned
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
     * Calculates the rent amount for the station property based on the number of stations owned by the owner.
     * @return The calculated rent amount, it doubles with each extra station.
     */
    public int calcRent() {
        return (int) (Math.pow(2, this.getOwner().getLandsOwnedOfType(Station.class).size() - 1)) * this.baseRent;
    }

    /**
     * Overrides the toString() method to provide a string representation of the Station object.
     * @return A string representation of the Station object.
     */
    @Override
    public String toString() {
        return "Station{" +
                "} " + super.toString();
    }
}
