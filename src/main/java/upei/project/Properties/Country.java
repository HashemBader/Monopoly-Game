package upei.project.Properties;

import upei.project.Player;

import java.util.HashMap;

/**
 * The Country class represents a country property on the game board.
 * It extends the Property class and implements specific behaviors for country properties.
 */
public class Country extends Property {
    private final String COLOR;
    public static final HashMap<String, Integer> colorSetMapper = new HashMap<>(){{
        put("brown", 2);
        put("lightBlue", 3);
        put("pink", 3);
        put("orange", 3);
        put("red", 3);
        put("yellow", 3);
        put("green", 3);
        put("darkBlue", 2);
    }};

    /*
    * If color set is complete -> rent *2
    * for each country check colorsOwned(color, completeSetVal)
    * if colors owned == completeSetValue then double the rent
    * */
    /**
     * Constructor to create a Country instance with specified parameters.
     * @param iLoc Location index of the country property on the board.
     * @param name Name of the country property.
     * @param rent Rent amount for landing on the property.
     *
     * @param buyPrice Buying price of the country property.
     */
    public Country(int iLoc, String name, int rent, int buyPrice){
        super(iLoc, name, buyPrice, rent);// Calls the constructor of the superclass 'Property'
        this.COLOR = "default";
    }
    public Country(int iLoc, String name, int baseRent, int buyPrice, String color){
        super(iLoc, name, buyPrice, baseRent);// Calls the constructor of the superclass 'Property'
        this.COLOR = color;
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
            if (player.makeChoice(this)){// make a choice depend on the strategy
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{ // A player has landed on another player's property
            player.subtractMoney(this.calcRent());// Deduct rent from the current player
            getOwner().addMoney(this.calcRent());// Add rent to the property owner's money
        }
    }
    // Getter and Setter methods for various attributes


    public int calcRent(){
        if(this.getOwner().hasCompleteSet(this.COLOR, colorSetMapper.get(this.COLOR))){
            return this.baseRent * 15;
        }
        return this.baseRent;
    }

    public String getColor(){return this.COLOR;}

    /**
     * Calculates the mortgage price of the country property.
     * @return The mortgage price calculated as half of the property's buy price.
     */
    public int getMortgagePrice(){
        return this.buyPrice / 2;
    }


    @Override
    public String toString() {
        return "Country{" +
                "color=" + COLOR +
                "} " + super.toString();
    }
}
