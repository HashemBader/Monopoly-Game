package upei.project.Properties;

import upei.project.Player;
import java.util.HashMap;

/**
 * The Country class represents a country property on the game board.
 * It extends the Property class and implements specific behaviors for country properties.
 */
public class Country extends Property {

    // Fields
    private final String COLOR;// the color of the countries
    private int numHouses = 0;// number of houses, maximum 4
    private int housePrice;// the price of a house

    // Mapping of color sets to the number of properties in that set
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

    /**
     * Constructor to create a Country instance with specified parameters.
     * @param iLoc Location index of the country property on the board.
     * @param name Name of the country property.
     * @param baseRent Base rent amount for landing on the property.
     * @param buyPrice Buying price of the country property.
     * @param color Color category of the country property.
     */
    public Country(int iLoc, String name, int baseRent, int buyPrice, String color){
        super(iLoc, name, buyPrice, baseRent); // Calls the constructor of the superclass 'Property'
        this.COLOR = color;
        calcHousePrice();
    }

    /**
     * Determines the action to be taken when a player lands on a country property.
     * @param player The player object representing the player who landed on the property.
     */
    public void playerOnLocation(Player player) {
        if (this.getOwner() == player) { // the player is the owner
            if (player.hasCompleteSet(this.COLOR, colorSetMapper.get(this.COLOR))) {// checks if the player has a complete set of colors
                if (player.makeHouseChoice(this) && this.numHouses < 4) { // makes the player to choose if he wants to build a house and the number of houses should be less than 4 to build
                    this.numHouses += 1;// builds a house
                    player.subtractMoney(this.housePrice);// deduct the player's money by the price of the house
                }
            }
        }
        else if (this.getOwner() != player && this.getOwner() == null) { // Property not owned
            if (player.makeChoice(this)) {// make a choice depend on the strategy
                this.setOwner(player);// buys the property
                player.subtractMoney(this.getBuyPrice());// deduct the player's money by the price of the property
            }
        } else { // A player has landed on another player's property
            player.subtractMoney(this.calcRent());// Deduct rent from the current player
            getOwner().addMoney(this.calcRent());// Add rent to the property owner's money
        }
    }

    /**
     * Calculates the rent amount for the country property based on (complete set of colors, number of houses).
     * @return The calculated rent amount.
     */
    public int calcRent(){
        if(this.getOwner().hasCompleteSet(this.COLOR, colorSetMapper.get(this.COLOR))){// checks for complete set
            if(this.numHouses == 0)
                return this.baseRent * 2 ;// the rent of the complete set
            else if (this.numHouses == 1)
                return this.baseRent * 5 ;// the rent of 1 house
            else{
                return 3 * this.baseRent * 5 * (this.numHouses - 1) ;// the rent of more than 1 house
            }
        }
        return this.baseRent; // the rent of no complete set
    }

    /**
     * Retrieves the color category of the country property.
     * @return The color category of the property.
     */
    public String getColor(){return this.COLOR;}

    /**
     * Retrieves the number of houses built on the country property.
     * @return The number of houses.
     */
    public int getNumHouses(){
        return this.numHouses;
    }

    // Private helper method to calculate the price to build a house in a property based on color
    private void calcHousePrice(){
        switch (this.getColor()) {
            case "brown", "lightBlue" -> this.housePrice = 50;
            case "pink", "orange" -> this.housePrice = 100;
            case "red", "yellow" -> this.housePrice = 150;
            case "green", "darkBlue" -> this.housePrice = 200;
            default -> this.housePrice = -1; //error
        }
    }

    /**
     * Retrieves the price of building houses on the country property.
     * @return The price of building houses.
     */
    public int getHousePrice(){
        return this.housePrice;
    }

    /**
     * Overrides the toString() method to provide a string representation of the Country object.
     * @return A string representation of the Country object.
     */
    @Override
    public String toString() {
        return "Country{" +
                "COLOR='" + COLOR + '\'' +
                "} " + super.toString();
    }
}
