package upei.project.Properties;

import upei.project.Player;

/**
 * The Country class represents a country property on the game board.
 * It extends the Property class and implements specific behaviors for country properties.
 */
public class Country extends Property {
    // String color;
    private int rent; // Rent amount for landing on the property
    /**
     * Constructor to create a Country instance with specified parameters.
     * @param iLoc Location index of the country property on the board.
     * @param name Name of the country property.
     * @param rent Rent amount for landing on the property.
     * @param buyPrice Buying price of the country property.
     */
    public Country(int iLoc, String name, int rent, int buyPrice){
        super(iLoc, name, buyPrice);// Calls the constructor of the superclass 'Property'
        this.rent = rent;
    }
    /**
     * Determines the action to be taken when a player lands on a country property.
     * @param player The player object representing the player who landed on the property.
     */
    public void playerOnLocation(Player player){
        if (this.getOwner() == player){
            return;  // Do nothing if the current player is the owner
        }
        else if (this.getOwner() != player && this.getOwner() == null){ // Property not owned
            if (player.getMoney() > this.getBuyPrice() && player.makeChoice(this)){// make a choice depend on the strategy
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{ // A player has landed on another player's property
            player.subtractMoney(this.getRent());// Deduct rent from the current player
            getOwner().addMoney(this.getRent());// Add rent to the property owner's money
        }
    }
    // Getter and Setter methods for various attributes

    public int getRent() {return rent;} //todo based on colors


    /**
     * Calculates the mortgage price of the country property.
     * @return The mortgage price calculated as half of the property's buy price.
     */
    public int getMortgagePrice(){
        return (int)(this.buyPrice / 2);
    }

    @Override
    public String toString() {
        return "Country{" +
                "rent=" + rent +
                "} " + super.toString();
    }
}
